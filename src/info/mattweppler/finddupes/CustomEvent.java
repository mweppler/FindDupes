package info.mattweppler.finddupes;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

class CustomEvent extends EventObject
{
	private static final long serialVersionUID = 7107951334383733685L;
	public CustomEvent(Object source)
	{
		super(source);
	}
}

interface CustomListener extends EventListener
{
	public void customEventOccurred(CustomEvent event);
}

class CustomClass
{
	protected EventListenerList listenerList = new EventListenerList();

	public void addEventListener(CustomListener listener)
	{
		listenerList.add(CustomListener.class, listener);
	}
	public void removeEventListener(CustomListener listener)
	{
		listenerList.remove(CustomListener.class, listener);
	}
	public void fireEvent(CustomEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i+2) {
			if (listeners[i] == CustomListener.class) {
				((CustomListener) listeners[i+1]).customEventOccurred(event);
			}
		}
	}
}
