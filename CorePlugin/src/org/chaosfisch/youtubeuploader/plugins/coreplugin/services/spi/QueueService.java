/*
 * Copyright (c) 2012, Dennis Fischer
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.chaosfisch.youtubeuploader.plugins.coreplugin.services.spi;

import org.chaosfisch.youtubeuploader.plugins.coreplugin.models.Queue;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dennis
 * Date: 05.01.12
 * Time: 10:14
 * To change this template use File | Settings | File Templates.
 */
public interface QueueService
{
	String QUEUE_ADDED   = "queueAdded"; //NON-NLS
	String QUEUE_REMOVED = "queueRemoved"; //NON-NLS
	String QUEUE_UPDATED = "queueUpdated"; //NON-NLS

	/**
	 * Adds / Persists a Queue(Entry)
	 *
	 * @param queue the Queue(Entry) that should be added
	 * @return the added Queue(Entry)
	 */
	Queue create(Queue queue);

	Queue delete(Queue queue);

	Queue update(Queue queue);

	/**
	 * Assigns a new place / new sequence to the entry
	 *
	 * @param queue         the Queue(Entry) to reposition
	 * @param queuePosition the Position the Queue(Entry) should get
	 */
	void sort(Queue queue, QueuePosition queuePosition);

	List<Queue> getAll();

	List<Queue> getQueued();

	List<Queue> getArchived();

	Queue find(int identifier);

	/**
	 * Polls item and updates inProgress to true
	 *
	 * @return Polls current first positioned item or null if not found
	 */
	Queue poll();

	boolean hasStarttime();
}
