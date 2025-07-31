# BiteApp - Frontend 📱

Este es el frontend móvil de **BiteApp**, una aplicación de pedidos y entregas desarrollada como proyecto académico en la Universidad Nacional de Costa Rica.

Está construido con **Kotlin** y **Jetpack Compose**, siguiendo principios modernos de arquitectura para Android. Permite a usuarios explorar restaurantes, ordenar productos, gestionar pedidos y recibir notificaciones de entrega.

---

## 🚀 Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Android Jetpack (Navigation, ViewModel, LiveData)
- Retrofit (para consumo del backend)
- Firebase Cloud Messaging (notificaciones)
- Google Maps SDK (geolocalización)
- Coil (carga de imágenes)

---

## 📁 Estructura del proyecto

```
frontend-sierra/
├── ui/
│   ├── screens/
│   ├── components/
│   └── navigation/
├── data/
│   ├── models/
│   ├── network/
│   └── repository/
├── viewmodel/
├── MainActivity.kt
└── ...
```

---

## ⚙️ Instalación y ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/ismaoft/biteapp-frontend.git
cd biteapp-frontend
```

2. Abre el proyecto en **Android Studio**.

3. Conecta un emulador o dispositivo físico.

4. Ejecuta la aplicación con el botón ▶️ o desde terminal:

```bash
./gradlew installDebug
```

---

## 🔐 Configuración de entorno (opcional)

Si usás servicios como Firebase o APIs protegidas, asegurate de agregar:

- `google-services.json` en `app/`
- Variables de entorno para claves de API en el archivo `local.properties` o como constantes en archivos seguros

---

## 🎓 Contexto académico

> Este proyecto fue desarrollado por **Ismael Salazar Blanco** como parte del curso *Desarrollo Móvil* en la Universidad Nacional de Costa Rica (2024). Aunque fue planteado como un trabajo colaborativo, el frontend fue implementado de forma independiente como práctica integral.

---

## 🙌 Créditos y contacto

Desarrollado por: **Ismael Salazar Blanco**  
Portafolio: https://ismael-salazar-blanco-portfolio.onrender.com
Correo: ismaoft@gmail.com

---


