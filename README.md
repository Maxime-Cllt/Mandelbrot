<p align="center">
  <img width="250px" height="250px" src="https://github.com/Maxime-Cllt/Mandelbrot/blob/main/documentation/image/F1.png" alt="Mandelbrot" />
  <img width="250px" height="250px" src="https://github.com/Maxime-Cllt/Mandelbrot/blob/main/documentation/image/F2.png" alt="Mandelbrot" />
  <img width="250px" height="250px" src="https://github.com/Maxime-Cllt/Mandelbrot/blob/main/documentation/image/F3.png" alt="Mandelbrot" />
</p>


# Calcul Distribué de l'Ensemble de Mandelbrot avec Java RMI

## Aperçu

Ce projet implémente la génération de l'ensemble de Mandelbrot en utilisant
une approche de calcul distribué avec Java RMI. L'ensemble de Mandelbrot est un
fractal célèbre en mathématiques et cette implémentation permet le calcul parallèle de l'ensemble en utilisant une
architecture distribuée.

Une version réalisé en C++ en utilisant MPI est disponible dans ce repository : <a href="https://github.com/Maxime-Cllt/Fractalium">Fractalium</a>

## Fonctionnalités

- **Calcul Distribué :** Utilise Java RMI pour la répartition du calcul sur plusieurs nœuds.

- **Génération de l'Ensemble de Mandelbrot :** Calcule et visualise l'ensemble de Mandelbrot en parallèle.

- **Paramètres Configurables :** Configurez facilement la résolution, le niveau de zoom et d'autres paramètres pour
  explorer différentes parties de l'ensemble de Mandelbrot.

## Prérequis

- Java 8 ou +
- Git

## Compatibilité

<img src="https://img.shields.io/badge/OS-MacOS-informational?style=flat&logo=apple&logoColor=white&color=2bbc8a" alt="MacOS" />
<img src="https://img.shields.io/badge/OS-Linux-informational?style=flat&logo=linux&logoColor=white&color=2bbc8a" alt="Linux" />
<img src="https://img.shields.io/badge/OS-Windows-informational?style=flat&logo=windows&logoColor=white&color=2bbc8a" alt="Windows" />


## Démarrage

1. **Cloner le Dépôt :**

```bash
git clone https://github.com/Maxime-Cllt/Mandelbrot.git
```

2. **Serveur :**

Il faut d'abord lancer le serveur sur la machine qui va afficher l'ensemble de Mandelbrot.

 ```bash
make
```

3. **Client :**

   Cette commande va lancer le client qui va se connecter au serveur et lancer les calculs.

```bash
./exec.sh
```

