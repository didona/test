package controllerTas.wpm;   /*
 * INESC-ID, Instituto de Engenharia de Sistemas e Computadores Investigação e Desevolvimento em Lisboa
 * Copyright 2013 INESC-ID and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3.0 of
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

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 01/02/13
 */

import controllerTas.controller.TasController;
import eu.cloudtm.wpm.connector.WPMConnector;
import eu.cloudtm.wpm.logService.remote.events.PublishViewChangeEvent;
import eu.cloudtm.wpm.logService.remote.events.SubscribeEvent;
import eu.cloudtm.wpm.logService.remote.listeners.WPMStatisticsRemoteListener;
import eu.cloudtm.wpm.logService.remote.listeners.WPMViewChangeRemoteListener;
import eu.cloudtm.wpm.logService.remote.observables.Handle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.rmi.RemoteException;
import java.util.Arrays;

public class TasWPMViewChangeRemoteListenerImpl implements
        WPMViewChangeRemoteListener {

   private WPMConnector connector;
   private final static Log log = LogFactory.getLog(TasWPMViewChangeRemoteListenerImpl.class);
   private Handle lastVmHandle;
   private TasController tasController;

   public TasWPMViewChangeRemoteListenerImpl(WPMConnector connector, TasController tasController) {
      this.connector = connector;
      this.tasController = tasController;
   }

   @Override
   public void onViewChange(PublishViewChangeEvent event)
           throws RemoteException {

      if (lastVmHandle != null) {
         log.trace("Removing last handle");
         connector.removeStatisticsRemoteListener(lastVmHandle);
         lastVmHandle = null;
      }
      String[] VMs = event.getCurrentVMs();

      if (VMs == null) {
         log.trace("The set of VMs is empty. No-op");
         return;
      }

      WPMStatisticsRemoteListener listener = new TasWPMStatisticsRemoteListenerImpl(tasController);

      log.trace("New set of VMs " + Arrays.toString(VMs));

      lastVmHandle = connector.registerStatisticsRemoteListener(new SubscribeEvent(VMs), listener);
   }


}
