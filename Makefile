all:
	clear
	javac server/*.java server/obj/*.java client/*.java
	java server.Serveur

perf: # Pour des performances optimales
	clear
	javac -O -g:none server/*.java server/obj/*.java client/*.java
	java -server -Xmx2g -Xms512m -XX:MaxGCPauseMillis=50 -XX:+UseParallelGC -XX:+UseCompressedClassPointers server.Serveur

clean:
	rm server/*.class
	rm server/obj/*.class
	rm client/*.class
	
