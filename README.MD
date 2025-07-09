# 🔐 Authentication API - OriFy

API de autenticação.<br>
**Stacks:** Ktor, MongoDB, Bcrypt

---

## ⚙️ Configuração das Variáveis de Ambiente

Antes de rodar a aplicação, defina as seguintes variáveis de ambiente:

| 🏷️ Variável                   | 📝 Descrição                              | 💡 Exemplo                                      |
|-------------------------------|-------------------------------------------|-------------------------------------------------|
| `MONGODB_CONNECTION_STRING`   | String de conexão do MongoDB              | `mongodb+srv://usuario:senha@host/db?retryWrites=true&w=majority` |
| `MONGODB_DATABASE_AUTH`       | Nome do banco de dados de autenticação    | `authentication`                                |
| `MONGODB_USERS_COLLECTION`    | Nome da coleção de usuários (opcional)    | `users`                                         |

Você pode definir as variáveis no terminal:

```sh
export MONGODB_CONNECTION_STRING="mongodb+srv://usuario:senha@host/db?retryWrites=true&w=majority"
export MONGODB_DATABASE_AUTH="authentication"
export MONGODB_USERS_COLLECTION="users"
```

Ou criar um arquivo `.env` (se usar um loader de env):

```
MONGODB_CONNECTION_STRING=mongodb+srv://usuario:senha@host/db?retryWrites=true&w=majority
MONGODB_DATABASE_AUTH=authentication
MONGODB_USERS_COLLECTION=users
```

---

## 🚦 Endpoints

### 📝 Registro de Usuário

- **POST** `/register`
- **Body:**
    ```json
    {
      "email": "seu@email.com",
      "username": "seunome",
      "password": "suaSenha@123"
    }
    ```
- **Respostas:**
    - `201` ✅ Usuário registrado com sucesso
    - `409` ⚠️ Email já está em uso ou nome de usuário indisponível
    - `400` ❌ Dados inválidos

---

### 🔑 Login

- **POST** `/login`
- **Body:**
    ```json
    {
      "identifier": "seu@email.com ou seunome",
      "password": "suaSenha@123"
    }
    ```
- **Respostas:**
    - `200` ✅ Login realizado com sucesso
    - `401` ❌ Credenciais inválidas

---

## ▶️ Como rodar

1. 📦 Instale as dependências:
    ```sh
    ./gradlew build
    ```
2. 🛠️ Defina as variáveis de ambiente conforme acima.
3. 🚀 Rode a aplicação:
    ```sh
    ./gradlew run
    ```

---

## 💬 Dúvidas ou sugestões?

Abra uma issue! Ficarei feliz em ajudar.
