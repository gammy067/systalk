package com.systalk.sys.controller.exception;

public class StRuntimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5920786778701336181L;

	private Object [] args;
	private String msgKey;
	
	public StRuntimeException(){
		super();
	}
	
    /**
     * Instantiates a new st runtime exception.
     *
     * @param cause the cause
     */
    public StRuntimeException(Throwable cause) {
        super(cause);
    }
	
	/**
	 * <p>
	 * msgKey放的是message_locale.properties中的Message Key<br>
	 * eg:String msg = messageUtil.getMessage(e.getMsgKey());
	 * </p>
	 * <p>
	 * args放的是要取代message中的參數<br>
	 * eg:String msg = messageUtil.getMessage(e.getMsgKey(),e.getArgs());
	 * <p>
	 * @param msgKey
	 * @param args
	 */
	public StRuntimeException(String msgKey, Object [] args){
		this.args = args;
		this.msgKey = msgKey;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	
	
}
