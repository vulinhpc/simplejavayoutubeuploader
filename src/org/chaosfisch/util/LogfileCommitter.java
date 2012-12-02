package org.chaosfisch.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class LogfileCommitter
{
	public static void commit()
	{
		getUniqueId();
	}

	private static String getUniqueId()
	{
		try
		{
			final InetAddress address = InetAddress.getLocalHost();

			/*
			 * Get NetworkInterface for the current host and then read the
			 * hardware address.
			 */
			final NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null)
			{
				final byte[] mac = ni.getHardwareAddress();
				if (mac != null)
				{
					final StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++)
					{
						sb.append(String.format("%02X%s", mac[i], (i < (mac.length - 1)) ? "-" : ""));
					}
					System.out.println(sb.toString());
				} else
				{
					System.out.println("Address doesn't exist or is not " + "accessible.");
				}
			} else
			{
				System.out.println("Network Interface for the specified " + "address is not found.");
			}
		} catch (final UnknownHostException | SocketException e)
		{

			// @TODO
		}

		return "";
	}
}
