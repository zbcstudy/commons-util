package com.wondertek.core.util.queue;

public interface Callback<T extends QueueAble> {
	public void callback(T queueAble);
}