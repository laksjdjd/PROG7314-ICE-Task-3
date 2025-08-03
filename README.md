# 🍳 CulinaryGPT Android Chatbot App

Welcome to **CulinaryGPT for Android**!  
This app is a friendly front-end to the powerful [CulinaryGPT Chatbot Server](https://github.com/SashveerRamjathan/PROG7314-Cohere-Chatbot-Server). Ask anything about cooking, recipes, food safety, nutrition, kitchen equipment, and more — CulinaryGPT is here to help you cook smarter, safer, and tastier.

---

## 📚 Table of Contents

- [✨ Features](#features)
- [⚡ Getting Started](#getting-started)
- [🛠️ Tech Stack](#tech-stack)
- [📁 Project Structure](#project-structure)
- [🔑 Authentication](#authentication)
- [🌐 API Server Integration](#api-server-integration)
- [🤓 Usage](#usage)
- [👤 Author & Module Info](#author--module-info)
- [👥 Contributing](#contributing)
- [🙏 Acknowledgements](#acknowledgements)

---

## ✨ Features

- 🍽️ **Recipe Guidance:** Get traditional and modern recipes, meal ideas, and technique tips.
- 🥦 **Ingredient Substitutions:** Find creative alternatives for common allergies or missing ingredients.
- 🥗 **Nutrition & Health:** Receive dietary guidance and healthy eating tips.
- 🧑‍🍳 **Kitchen Equipment:** Learn about proper use, care, and selection of kitchen tools and appliances.
- 🦠 **Food Safety:** Access expert advice on food storage, spoilage, and safe handling.
- 🎓 **Culinary Techniques:** Get step-by-step guidance for professional and home cooking methods.
- 🤖 **Conversational Chatbot Interface:** Friendly, practical, and actionable advice for cooks of all skill levels.
- 🚦 **Safety Warnings:** Built-in reminders when handling hot equipment, raw ingredients, or risky methods.
- 🔑 **Secure Authentication:**  
  - **Firebase Authentication**  
  - **Google Sign-In SSO** (Single Sign-On)  
  - **Biometric Authentication** (Anonymous sign-in with fingerprint/face unlock)

---

## ⚡ Getting Started

Get CulinaryGPT running on your Android device in a few easy steps:

### 🧰 Prerequisites

- Android Studio (Arctic Fox or newer recommended)
- Kotlin >= 1.8
- A connected Android device or emulator

### 🏗️ Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/SashveerRamjathan/PROG7314-ICE-Task-2.git
   cd PROG7314-ICE-Task-2
   ```

2. **Open in Android Studio:**
   - File > Open > Select the cloned folder.

3. **Build & Run:**
   - Click the ▶️ Run button in Android Studio.
   - Install on your device or launch the emulator.

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Framework:** Android SDK
- **Networking:** Retrofit (for API calls to the server)
- **UI:** Material Design Components
- **Authentication:**  
  - Firebase Authentication  
  - Google Sign-In SSO  
  - Biometric Authentication (AndroidX Biometric, anonymous sign-in)

---

## 📁 Project Structure

```
PROG7314-ICE-Task-2/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/             # Kotlin source code, incl. chatbot, auth, biometrics
│   │   │   ├── res/              # Layouts, drawables (includes biometric and Google icons)
│   │   │   └── AndroidManifest.xml
├── build.gradle
├── README.md
└── .gitignore
```

---

## 🔑 Authentication

CulinaryGPT now supports secure sign-in options:
- **Google Sign-In SSO:** Sign in with your Google account for a seamless experience.
- **Biometric Authentication:** Use your device's fingerprint or face unlock to sign in anonymously and securely.
- **Firebase Authentication:** All user sessions are securely managed with Firebase.

You can choose either Google SSO or biometric sign-in at the welcome screen.

---

## 🌐 API Server Integration

This app is a front-end client for the **CulinaryGPT Chatbot Server** (RAG-powered, Cohere-based), hosted on Vercel:

- **API Endpoint:**  
  `https://prog-7314-cohere-chatbot-server.vercel.app/prompt`
- **Health Check:**  
  `https://prog-7314-cohere-chatbot-server.vercel.app/health`
- **Analytics Dashboard:**  
  `https://prog-7314-cohere-chatbot-server.vercel.app/stats`

### 🔌 How it Works

- The Android app sends user questions to the `/prompt` endpoint as JSON:
  ```json
  {
    "prompt": "How do I make perfect pasta?"
  }
  ```
- The API server returns a structured response:
  ```json
  {
    "text": "To make perfect pasta, start with a large pot of salted water...",
    "citations": [
      { "start": 15, "end": 42, "document_id": "recipes_1" }
    ],
    "documentsUsed": 5,
    "categoriesReferenced": ["recipes", "techniques", "cooking_advice"]
  }
  ```
- The Android app displays the answer, including citations and advice.

### 🛡️ Server Features
- RAG-powered with semantic search for verified culinary advice
- Expert knowledge base across 7 categories
- Fast, context-aware responses with real-time analytics
- See [server README](https://github.com/SashveerRamjathan/PROG7314-Cohere-Chatbot-Server) for full details

---

## 🤓 Usage

- **Ask CulinaryGPT:** Type your cooking question into the chat and get instant answers.
- **Get Recipe Ideas:** Ask for recipes by cuisine, ingredient, or dietary need.
- **Find Substitutes:** Unsure what to use for a missing ingredient? Just ask!
- **Stay Safe:** Receive reminders for handling food safely and using kitchen equipment.
- **Learn Techniques:** Request breakdowns of culinary skills or troubleshooting help.
- **Nutrition Guidance:** Get health advice tailored for your cooking needs.
- **Sign In Easily:** Choose between Google SSO or biometric sign-in on the welcome screen for a secure chat experience.

---

## 👤 Author & Module Info

- **Name:** Sashveer Lakhan Ramjathan  
- **Student Number:** ST10361554  
- **Group:** 2  
- **Module:** PROG7314  
- **Assessment:** ICE Task 3  

---

## 👥 Contributing

Contributions are welcome!  
Open issues for suggestions or bugs, and submit pull requests for improvements.

---

## 🙏 Acknowledgements

- 🤖 [CulinaryGPT Chatbot Server](https://github.com/SashveerRamjathan/PROG7314-Cohere-Chatbot-Server) for providing expert culinary knowledge and AI responses
- ☁️ **Vercel** for seamless serverless deployment platform
- 📚 **Culinary experts** whose knowledge forms our comprehensive database
- 🔬 **RAG Architecture** pioneers for the retrieval-augmented generation concept
- 👨‍🍳 **Home cooks everywhere** who inspire us to make cooking accessible
- 🌟 **Open source community** for the incredible tools and libraries

---
