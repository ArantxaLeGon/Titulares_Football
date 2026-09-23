<div align="center">

# ⚽ Titulars ⚽

### Selección automática del equipo titular — Fútbol 5

![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-datasource-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)

</div>

---

## 📌 ¿Qué es?

Prueba técnica del taller asignado por el instructor **Michael Giraldo**. Registra el rendimiento de los jugadores por entrenamiento y, al completar la semana, determina automáticamente el equipo titular según el puntaje obtenido.

## 🧠 ¿En qué consiste?

Cada jugador realiza **3 entrenamientos** por semana. En cada uno se registran tres datos:

| 📊 Dato | Descripción | Peso |
|---|---|:---:|
| 🎯 Potencia de tiro (Km/h) | Velocidad con la que llega el balón a la portería | **20%** |
| 🏃 Velocidad del jugador (Km/h) | Velocidad a la que se desplaza el jugador | **30%** |
| 🎯 Pases efectivos | Cantidad de pases realizados correctamente | **50%** |

Con esos tres valores se calcula el **puntaje del entrenamiento**:

```
puntaje = (potencia × 0.20) + (velocidad × 0.30) + (pases × 0.50)
```

> Una vez un jugador tiene sus 3 entrenamientos registrados, el sistema promedia sus puntajes. Cuando **todos** los jugadores completaron sus 3 entrenamientos, se seleccionan los **5 jugadores con mejor puntaje** como titulares.
>
> Si algún jugador aún no tiene los 3 entrenamientos, el sistema avisa que no hay información suficiente en lugar de devolver un equipo titular.

## 🛠️ Tecnologías

| Componente | Detalle |
|---|---|
| Lenguaje | Java 25 |
| Framework | Spring Boot 4.1.1 (Web, Data JPA) |
| Base de datos | MySQL |
| Build | Maven |

## ✅ Requisitos previos

- [ ] JDK 25 instalado
- [ ] MySQL en ejecución (local o remoto)
- [ ] Maven (o usar el `mvnw` incluido en el proyecto)

## 🗄️ Configuración de la base de datos

El proyecto tiene `ddl-auto: none`, así que la tabla debe crearse antes de levantarlo:

```sql
CREATE DATABASE IF NOT EXISTS equipFootball;

USE equipFootball;

CREATE TABLE Equip (
    id_player      BIGINT NOT NULL,
    num_training   BIGINT NOT NULL,
    power          DOUBLE,
    speed          DOUBLE,
    passing        DOUBLE,
    points_training DOUBLE,
    PRIMARY KEY (id_player, num_training)
);
```

Luego ajusta las credenciales en `src/main/resources/application.yaml` si son distintas a las de tu entorno local:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/equipFootball
    username: root
    password:
```

## 🚀 Instalación y ejecución

```bash
# Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>
cd titulars

# Ejecutar el proyecto
./mvnw spring-boot:run
```

El servicio queda disponible en:

```
http://localhost:7070
```

## 📡 Endpoints

### 1️⃣ Registrar entrenamiento

Guarda el resultado de un entrenamiento para un jugador.

```http
POST http://localhost:7070/report/create
Content-Type: application/json
```

<details>
<summary><strong>Body de ejemplo</strong></summary>

```json
{
  "idPlayer": 1,
  "numTraining": 1,
  "power": 10,
  "speed": 5,
  "passing": 25
}
```

</details>

<details>
<summary><strong>Respuesta — 201 Created</strong></summary>

```json
{
  "idPlayer": 1,
  "numTraining": 1,
  "power": 10,
  "speed": 5,
  "passing": 25,
  "pointsTraining": 16.0,
  "finalPoints": 0.0
}
```

</details>

> 🔁 Repite este paso por cada jugador, 3 veces (`numTraining`: 1, 2 y 3).

### 2️⃣ Consultar equipo titular

Devuelve los 5 jugadores con mejor puntaje, siempre que todos tengan sus 3 entrenamientos registrados.

```http
GET http://localhost:7070/report/starting_players
```

<details>
<summary><strong>Respuesta — 200 OK</strong></summary>

```json
[
  {
    "idPlayer": 3,
    "numTraining": 3,
    "power": 15.0,
    "speed": 3.0,
    "passing": 30.0,
    "pointsTraining": 18.9,
    "finalPoints": 18.9
  }
]
```

</details>

> ⚠️ Si algún jugador no completó sus 3 entrenamientos, el servicio informa que no hay datos suficientes para calcular el equipo titular.

## 🧪 Cómo probarlo (Postman)

1. Crea una petición `POST` a `http://localhost:7070/report/create` con el body de ejemplo y envíala una vez por cada entrenamiento de cada jugador (3 por jugador).
2. Con todos los entrenamientos registrados, crea una petición `GET` a `http://localhost:7070/report/starting_players` para ver el listado de titulares.

## 📂 Estructura del proyecto

```
src/main/java/com/equip/titulars
 ├── 🎮 controller   → Expone los endpoints REST
 ├── ⚙️  service      → Lógica de negocio (cálculo de puntaje y selección de titulares)
 ├── 💾 repository   → Acceso a datos (JPA)
 ├── 🧱 entity       → Entidades de base de datos
 └── 📦 dto          → Objetos de entrada/salida de la API
```

---

<div align="center">

Proyecto desarrollado como prueba técnica — SENA ADSO

</div>
