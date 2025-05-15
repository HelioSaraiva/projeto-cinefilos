# 🎬 Projeto Cinéfilos

Aplicativo Android nativo desenvolvido em **Kotlin**, que consome a API do [The Movie Database (TMDB)](https://www.themoviedb.org/documentation/api) para apresentar:

- 🎥 **Filmes populares**
- 🆕 **Filmes em lançamento**
- 📱 Interface fluida, com **duas listas horizontais (RecyclerViews)**
- 💡 Código limpo e pronto para evolução com MVVM, Room e mais

---

## 📸 Preview do App


![image](https://github.com/user-attachments/assets/b6a1dd9d-4e08-4d01-99f1-f6e8b453d0d4)
![image](https://github.com/user-attachments/assets/73032e2d-e625-479f-8323-0d43ee5900d5)
![image](https://github.com/user-attachments/assets/2805753e-30d0-4f3e-a298-42b68e6fdeaa)
![image](https://github.com/user-attachments/assets/b187f962-5fc1-4fe6-8bda-e4768aef6e24)
![image](https://github.com/user-attachments/assets/7b7a9988-7360-4c09-9cb3-5e87fb485f35)





---

## 🚀 Funcionalidades

✅ Login fictício (tela de entrada)  
✅ Tela principal com:
- Lista horizontal de **filmes em cartaz**
- Lista horizontal de **filmes populares**
✅ Navegação para tela de detalhes do filme  
✅ Layout individual para cada item (imagem, título, nota)  
✅ Design responsivo com Material Design 3  
✅ Consumo de API via **Retrofit + Gson**

---

## 🛠️ Tecnologias Utilizadas

- [x] Kotlin  
- [x] Android SDK (API 21+)  
- [x] ViewBinding  
- [x] Retrofit2  
- [x] Gson  
- [x] RecyclerView  
- [x] API TMDB  
- [x] Navegação com `Intent`

---

## 🧩 Estrutura de Telas

```
├── LoginActivity.kt
├── MainActivity.kt
├── DetalhesActivity.kt
└── /res/layout
    ├── activity_login.xml
    ├── activity_main.xml
    ├── activity_detalhes.xml
    └── item_filme.xml        # layout de cada filme na lista
```

---

## ⚙️ Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/projeto-cinefilos.git
```

2. Abra no **Android Studio (Arctic Fox ou superior)**

3. Crie uma chave da TMDB em:  
   https://www.themoviedb.org/settings/api

4. Adicione sua API key no arquivo:
```kotlin
// Constants.kt
const val TMDB_API_KEY = "SUA_CHAVE_AQUI"
```

5. Execute o projeto em um emululador ou dispositivo físico

---

## 💼 Destaques para Recrutadores

> Este projeto demonstra:

- Habilidade real em **consumo de API RESTful** (Retrofit)
- Uso avançado de **RecyclerView horizontal + múltiplas listas**
- Separação de responsabilidades entre telas
- Leitura de dados da internet com parsing de JSON
- Navegação com dados via `Intent.putExtra`
- Estrutura modular, ideal para evolução com **MVVM**, **Room**, ou **Jetpack Compose**

🎯 Indicado para vagas de **Desenvolvedor Android Júnior / Intermediário**

---

## 📈 Possíveis melhorias

- [ ] Autenticação real com Firebase Auth  
- [ ] Tela de favoritos com Room Database  
- [ ] Testes unitários e instrumentados  
- [ ] Migração para Jetpack Compose (modular)  
- [ ] Implementar arquitetura MVVM com ViewModel e LiveData

---

## 📄 Licença

Este projeto está sob a licença MIT.  
Sinta-se livre para estudar, modificar e usar no seu portfólio!

---

## 🙋 Sobre o autor

Desenvolvido por **[Helio Buzato]** – apaixonado por Android, APIs e interfaces bem-feitas.

📧 contato: buzato@hotmail.com  
🔗 [LinkedIn](https://linkedin.com/in/heliosaraivabuzato/) | [GitHub](https://github.com/HelioSaraiva)
