all:
	# Compilation des fichiers java
	javac server/*.java
	javac server/obj/*.java
	javac client/*.java
	# Lancement du rmiregistry dans un script perso
	#./server/rmi.sh
	# Lancement du serveur
	java server.Serveur

clean:
	rm server/*.class
	rm server/obj/*.class
	rm client/*.class
	
