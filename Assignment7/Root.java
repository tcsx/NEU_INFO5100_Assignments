public class Root {
    public static void main(String[] args) {

        Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);

        Controller controller = new Controller(device, heat, pressure);

        controller.start();
        heat.start();
        pressure.start();
    }
}

class Controller extends Thread {
    Device device;
    Sensor heat;
    Sensor pressure;

    public Controller(Device device, Sensor heat, Sensor pressure) {
        this.device = device;
        this.heat = heat;
        this.pressure = pressure;
    }

    @Override
    public void run() {
        device.startup();
        double temp = heat.getValue();
        double press = pressure.getValue();
        synchronized (pressure) {
            synchronized (heat) {
                while (true) {
                    temp = heat.getValue();
                    press = pressure.getValue();
                    System.out.printf("heat -> %.2f , pressure -> %.2f\n", temp, press);
                    if (temp > 70 || press > 100) {
                        heat.interrupt();
                        pressure.interrupt();
                        device.shutdown();
                        return;
                    }
                    try {
                        heat.wait();
                        pressure.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}

class Device {
    public void startup() {
        System.out.println("Device started.");
    }

    public void shutdown() {
        System.out.println("Device shutting down due to maintenance.");
    }
}

class Sensor extends Thread {
    private final Device device;
    private double value;

    public Sensor(Device device) {
        this.device = device;
    }

    public double getValue() {
        return value;
    }

    public void updateValue() {
        this.value += 0.01;
    }

    public void run() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!isInterrupted()) {
            updateValue();
            synchronized (this) {
                notify();
            }
        }
    }
}