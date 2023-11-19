#!/bin/bash

# Pour lancer le registre RMI (rmiregistry) sur le port 1099
PID=$(pidof rmiregistry)
if [ -z "$PID" ]
then
  echo "Lancement du registre RMI ..."
  rmiregistry &
else
  kill -9 "$PID"
  rmiregistry &
  echo "Redémarrage du registre RMI ..."
fi
