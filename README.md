# AB_MESSENGER

[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/github/workflow/status/usuario/AB_MESSENGER/CI)](https://github.com/usuario/AB_MESSENGER/actions)

AB_MESSENGER é uma API desenvolvida com **Spring Boot** para envio de **E-mails** e **SMS**. Com o objetivo de simplificar e centralizar o envio de notificações em sua aplicação, o AB_MESSENGER oferece endpoints para envio de mensagens via E-mail e SMS de maneira prática e eficiente.

---

## 📦 Funcionalidades

- **Envio de E-mails**: Suporte para envio de mensagens de e-mail em formato HTML e texto.
- **Envio de SMS**: Envio de mensagens SMS para números de telefone em diferentes operadoras.
- **Autenticação e Segurança**: API segura utilizando **JWT** para autenticação de usuários.
- **Monitoramento**: Logs detalhados para acompanhamento do status de envio de mensagens.
- **Escalabilidade**: Configuração fácil para integração com múltiplos provedores de e-mail e SMS.

---

## 🚀 Como Usar

1. Clone este repositório:

    ```bash
    git clone https://github.com/usuario/AB_MESSENGER.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd AB_MESSENGER
    ```

3. Configure as variáveis de ambiente no arquivo `application.properties` para suas credenciais de e-mail e SMS. Exemplo:

    ```properties
    spring.mail.host=smtp.exemplo.com
    spring.mail.port=587
    spring.mail.username=seu-email@exemplo.com
    spring.mail.password=sua-senha

    sms.apiKey=SuaApiKeySms
