# 🃏 UNO em Java

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Architecture](https://img.shields.io/badge/Arquitetura-MVC-blue?style=for-the-badge)
![Interface](https://img.shields.io/badge/Interface-Console-lightgrey?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)

> Implementação do clássico jogo de cartas **UNO** em Java com arquitetura MVC, dois modos de baralho, suporte a 2–10 jogadores e todas as cartas especiais das regras oficiais.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura](#-arquitetura)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Como Jogar](#-como-jogar)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Autor](#-autor)

---

## 📖 Sobre o Projeto

Jogo UNO para terminal desenvolvido em Java, seguindo o padrão **MVC (Model-View-Controller)**. O projeto oferece dois modos de baralho: o **Baralho UNO Oficial** (108 cartas) e o **Baralho Convencional** (52 cartas), onde as cartas especiais são mapeadas para figuras do baralho tradicional (J, Q, K e Joker).

---

## ✅ Funcionalidades

- [x] Dois modos de baralho: **UNO Oficial** e **Convencional (52 cartas)**
- [x] Suporte a **2 até 10 jogadores**
- [x] Embaralhamento e distribuição automática de 7 cartas por jogador
- [x] Cartas especiais com efeitos implementados (Pular, Inverter, +2, Coringa, Coringa +4)
- [x] Mecânica de **gritar UNO** com penalidade de +2 cartas para quem esquecer
- [x] Escolha de cor ao jogar carta Coringa
- [x] Reciclagem automática da pilha de descarte quando o baralho esgota
- [x] Exibição da mão do jogador com tradução para o baralho convencional

---

## 🏗️ Arquitetura

O projeto segue o padrão **MVC**, separando claramente as responsabilidades:

| Camada | Responsabilidade |
|---|---|
| **Model** | Entidades do jogo: `Carta`, `Jogador`, `Baralho`, enums `Cor` e `Valor` |
| **View** | Interface com o jogador via terminal (`ConsoleUNO`) e tradução de cartas (`TradutorBaralho`) |
| **Controller** | Lógica da partida: turnos, validações, efeitos e vitória (`Jogo`) |

---

## ⚙️ Pré-requisitos

- [Java JDK](https://www.oracle.com/java/technologies/downloads/) versão **11 ou superior**

```bash
# Verifique sua versão instalada
java -version
```

---

## 🚀 Como Executar

**1. Clone o repositório:**
```bash
git clone https://github.com/CaueFAbreu/UNO.git
cd UNO
```

**2. Compile os arquivos Java:**
```bash
javac -d out/production/UNO src/Main.java src/controller/*.java src/model/*.java src/view/*.java
```

**3. Execute o jogo:**
```bash
java -cp out/production/UNO Main
```

> 💡 Se preferir, importe o projeto diretamente no **IntelliJ IDEA** — o arquivo `UNO.iml` já está configurado.

---

## 🎮 Como Jogar

Ao iniciar, o jogo solicitará:

1. **Modo de baralho** — Oficial (UNO) ou Convencional (52 cartas)
2. **Número de jogadores** (2 a 10) e os seus nomes

Durante cada turno, o jogador vê a carta no topo da mesa e suas cartas na mão. As opções disponíveis são:

| Comando | Ação |
|---|---|
| `0`, `1`, `2`... | Jogar a carta do índice correspondente |
| `C` | Comprar uma carta e passar a vez |
| `U` | Gritar **UNO!** antes de jogar a última carta |

**Cartas especiais:**

| Carta | Efeito |
|---|---|
| 🚫 Pular | O próximo jogador perde a vez |
| 🔄 Inverter | O sentido dos turnos é invertido |
| +2 | O próximo jogador compra 2 cartas e perde a vez |
| 🃏 Coringa | Quem jogou escolhe a nova cor ativa |
| 🃏 Coringa +4 | Escolha de cor + próximo jogador compra 4 cartas e perde a vez |

> ⚠️ **Penalidade:** Se restar 1 carta na sua mão e você não gritar `U` antes de jogar, levará +2 cartas de punição.

---

## 📁 Estrutura do Projeto

```
📦 UNO
 ┣ 📂 src
 ┃ ┣ 📄 Main.java                      # Ponto de entrada e loop principal do jogo
 ┃ ┣ 📂 controller
 ┃ ┃ ┗ 📄 Jogo.java                    # Lógica da partida (turnos, validações, efeitos)
 ┃ ┣ 📂 model
 ┃ ┃ ┣ 📄 Carta.java                   # Classe abstrata base para todas as cartas
 ┃ ┃ ┣ 📄 CartaNumero.java             # Cartas numéricas (0–9)
 ┃ ┃ ┣ 📄 CartaAcao.java              # Cartas de ação (Pular, Inverter, +2)
 ┃ ┃ ┣ 📄 CartaCuringa.java           # Cartas Coringa e Coringa +4
 ┃ ┃ ┣ 📄 Baralho.java                # Classe abstrata de baralho
 ┃ ┃ ┣ 📄 BaralhoUnoOficial.java      # Baralho com 108 cartas (regras oficiais)
 ┃ ┃ ┣ 📄 BaralhoUnoConvencional.java # Baralho de 52 cartas adaptado
 ┃ ┃ ┣ 📄 Jogador.java                # Representa um jogador e sua mão
 ┃ ┃ ┣ 📄 Cor.java                    # Enum: VERMELHO, AMARELO, VERDE, AZUL, PRETO
 ┃ ┃ ┗ 📄 Valor.java                  # Enum: ZERO–NOVE, PULAR, INVERTER, MAIS_DOIS, CORINGA...
 ┃ ┗ 📂 view
 ┃   ┣ 📄 ConsoleUNO.java             # Interface com o usuário via terminal
 ┃   ┗ 📄 TradutorBaralho.java        # Traduz cartas UNO para o formato convencional
 ┗ 📄 UNO.iml                         # Configuração do projeto IntelliJ IDEA
```

---

## 👤 Autor

**Cauê F. Abreu**

[![GitHub](https://img.shields.io/badge/GitHub-CaueFAbreu-181717?style=flat&logo=github)](https://github.com/CaueFAbreu)

---

<p align="center">Feito com ☕ e Java</p>
