graph TD
    A[Bean Instantiation] --> B[Property Injection]
    B --> C[Set Bean Name]
    C --> D[Set Bean Factory]
    D --> E[Set Application Context]
    E --> F[Pre-Initialization]
    F --> G[Initialization]
    G --> H[Post-Initialization]
    H --> I[Bean is Ready for Use]
    I --> J[Destruction]
    J --> A
