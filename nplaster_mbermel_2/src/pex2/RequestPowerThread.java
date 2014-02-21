package pex2;

public class RequestPowerThread implements Runnable {
	private Pump currentpump;
	private Generator generator;
	private boolean startPumping;

	public RequestPowerThread(Pump pump, Generator generator) {
		currentpump = pump;
		this.generator = generator;
		startPumping = false;
	}

	@Override
	public void run() {
		currentpump.setStatus(Status.WAITING);
		for(int i = 0; i < 10; i++){
			if(generator.startGenerator()){
				startPumping = true;
				break;
			}
		}
		try {
			currentpump.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isStartPumping() {
		generator.currentlyGenerating();
		return startPumping;
	}

}