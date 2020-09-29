package nl.rabobank.powerofattorney.app.response;

public class ResponseWrapper {

    private boolean success;
    private Object data;

    private ResponseWrapper(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static ResponseWrapper success(Object data) {
        return new ResponseWrapper(true, data);
    }


    public static ResponseWrapper error() {
        return new ResponseWrapper(false, "Generic api failure");
    }


    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

}
