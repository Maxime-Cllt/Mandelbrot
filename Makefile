all:
#kill -9 $$(pidof rmiregistry)
	javac server/*.java
	javac client/*.java
	./server/rmi.sh
	java server.Serveur

clean:
	rm *.class
	
