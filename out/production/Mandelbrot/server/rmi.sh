#!/bin/bash
PID=$(pidof rmiregistry)
if [ -z "$PID" ]
then
  echo "Pas de registre en cours"
  rmiregistry &
else
  kill -9 "$PID"
  rmiregistry &
  echo "Relance du registre"
fi
sleep 0.5
echo ""