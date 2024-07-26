# Central Vision System

A mobile application for controlling a vision system, developed using Android Jetpack Compose. This app provides real-time control and thermal & normal image data from the vision system through WebSocket communication.
AsteriaTech Mobile is an innovative project that leverages advanced WebSocket technology for real-time data transmission. The project features a vision system equipped with a servo motor for horizontal movement, and is outfitted with both normal and thermal cameras. These cameras capture data from their surroundings and transmit it to a server, which processes the information and sends it to an Android client.
When the thermal camera detects any living presence, a "Detected" message appears on the Android screen. Users can send commands from the Android app to turn the vision system left or right, allowing for easy adjustment of the system's direction.
The vision system operates on a Raspberry Pi, while the backend server is developed using Spring. This combination provides the project with high performance and flexibility.


## Features
- **Real-time Control**: The app allows users to control the servo movements of the vision system in real time, enabling precise adjustments as needed.
- **Live Telemetry**: Receive live data from both normal and thermal cameras installed on the vision system, providing real-time insights into the environment.
- **WebSocket Communication**: Utilizes WebSocket for efficient and reliable real-time communication between the app and the drone's server, with two WebSocket channels to handle different types of data streams effectively.
- **User-friendly Interface**: Designed with an intuitive interface using Jetpack Compose, ensuring a seamless and easy-to-navigate experience for users of all skill levels.
- **Detection Alerts**: Provides immediate notifications on the Android device when the thermal camera detects a living presence, with a clear "Detected" message displayed on the screen.
- **Command Execution**: Users can send commands to adjust the orientation of the vision system, allowing for flexible control of the system's direction.
- **Efficient Data Handling**: The combination of Retrofit, Coil, and ExoPlayer ensures smooth data handling, image loading, and media playback functionalities within the app.
- **Modular Architecture**: Leveraging Clean Architecture and MVVM patterns for scalable and maintainable code, enhancing the overall stability and performance of the application.
- **Aspect-Oriented Programming (AOP)**: Utilized with annotations to manage cross-cutting concerns, improving modularity and separation of concerns in the application.



## Technologies Used
- **Android Jetpack Compose**: For modern, declarative UI development.
- **Kotlin**: The programming language used for Android development.
- **WebSocket**: For real-time communication between the app and the drone, utilizing two WebSocket channels for enhanced data handling.
- **Jetpack Dependency Injection with Hilt**: For managing dependencies in the Android app.
- **Retrofit**: For handling network requests and API interactions.
- **Coil**: For image loading and caching in the Android app.
- **ExoPlayer**: For media playback functionalities.
- **Clean Architecture**: For structuring the codebase in a scalable and maintainable manner.
- **MVVM**: Model-View-ViewModel pattern used for managing UI-related data in a lifecycle-conscious way.
- **AOP (Aspect-Oriented Programming)**: Utilized with annotations to manage cross-cutting concerns and improve modularity in the application.

# Project Presentation

## System Operation video

https://github.com/user-attachments/assets/f27daf81-2f54-4b01-9a8d-2659113ff472

## System Operation Overview

![photo_2024-07-26_16-23-04](https://github.com/user-attachments/assets/a291569e-dd2d-4490-a906-365101d949e1)






