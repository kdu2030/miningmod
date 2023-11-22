package kevin.miningmod.client.mining;

public class ClientMinerState {
    private boolean isClientMinerActivated;

    public ClientMinerState(){
        isClientMinerActivated = false;
    }

    public boolean isClientMinerActivated() {
        return isClientMinerActivated;
    }

    public void setClientMinerActivated(boolean clientMinerActivated) {
        isClientMinerActivated = clientMinerActivated;
    }
}
