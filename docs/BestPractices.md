### Best Practices

- Use `hibernate.ddl-auto = update` for DDL Operations and Liquibase for any DML Operations.
- **Packaging** : Create Packages by Feature
- Write Documentation Comments
- `README.md` in every package or directory.
- Go with Entity creation first
- Use DTO for Response & Requests, and Entity only for Repository/Database Interaction
- In case of multiple Entities involving for a single Request/Response **(for a small feature)**, Create a single DTO file and multiple DTO classes inside it. The same can be applied for Entities too. **(Only if the Entities/DTOs are small)** 