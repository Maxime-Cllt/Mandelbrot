<img
  width="50px"
  height="50px"
  src="https://github.com/Maxime-Cllt/Mandelbrot/assets/98154358/6121b166-82fa-4081-b953-7d542dac000e"
  alt="French" />

#  Calcul Distribué de l'Ensemble de Mandelbrot avec Java RMI


## Aperçu

Ce projet implémente la génération de l'ensemble de Mandelbrot en utilisant
une approche de calcul distribué avec Java RMI. L'ensemble de Mandelbrot est un
fractal célèbre en mathématiques et cette implémentation permet le calcul parallèle de l'ensemble en utilisant une
architecture distribuée.

## Fonctionnalités

- **Calcul Distribué :** Utilise Java RMI pour la répartition du calcul sur plusieurs nœuds.

- **Génération de l'Ensemble de Mandelbrot :** Calcule et visualise l'ensemble de Mandelbrot en parallèle.

- **Paramètres Configurables :** Configurez facilement la résolution, le niveau de zoom et d'autres paramètres pour
  explorer différentes parties de l'ensemble de Mandelbrot.

## Prérequis

- Java 8 ou +
- Git

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

<br>

<img
  width="50px"
  height="50px"
  src="https://github.com/Maxime-Cllt/Mandelbrot/assets/98154358/8f443a15-4d8d-48a4-8277-499c38165633"
  alt="English" />

# Distributed Calculation of the Mandelbrot Set with Java RMI

## Overview

This project implements the generation of the Mandelbrot set using a distributed computing approach with Java RMI. The Mandelbrot set is a famous fractal in mathematics, and this implementation allows parallel computation of the set using a distributed architecture.

## Features

- **Distributed Calculation:** Uses Java RMI for distributing the computation across multiple nodes.

- **Mandelbrot Set Generation:** Computes and visualizes the Mandelbrot set in parallel.

- **Configurable Parameters:** Easily configure resolution, zoom level, and other parameters to explore different parts of the Mandelbrot set.

## Prerequisites

- Java 8 or higher
- Git

## Getting Started

1. **Clone the Repository:**

```bash
  git clone https://github.com/Maxime-Cllt/Mandelbrot.git
```

2. **Server :**

First, launch the server on the machine that will display the Mandelbrot set.

```bash
make
```

3. **Client :**

This command will launch the client that connects to the server and initiates the calculations.

```bash
./exec.sh
```

