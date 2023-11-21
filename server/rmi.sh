#!/bin/bash

# Pour lancer le registre RMI (rmiregistry) sur le port 1099
PID=$(pidof rmiregistry)
if [ -z "$PID" ]
then
  rmiregistry &
  PID=$!
  echo "Registre RMI lancé avec PID : $PID"
else
  echo "Arrêt du registre RMI avec PID : $PID"
  kill -9 "$PID"
  rmiregistry &
  NEW_PID=$!
  echo "Redémarrage du registre RMI avec nouveau PID : $NEW_PID"
fi

