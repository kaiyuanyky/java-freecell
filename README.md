# Simple FreeCell
This game is a simplified version of FreeCell. You may play a few FreeCell online to have some ideas.

## About this Game
1. This game requires only one deck of 52 unique cards. There are four suits namely diamonds (D), clubs (C), hearts (H), and spades (S).
2. Each card has a point value based on its face. For example, card with face 2 has 2 points. For A, J, Q, and K, its point values are listed in the following table.

**Face** |  A | 10 (X) | J | Q | K
---|---|---|---|---|---
**Point** |  1 | 10 | 11 | 12 | 13

3. The game has four (4) Piles and nine (9) Columns.
4. At the beginning of a game, the Piles are empty while Columns 1-8 are placed with random cards. A sample Command Prompt output is shown below.

```
Pile c: []
Pile d: []
Pile h: []
Pile s: []
Column 1: [c9, h5, dQ, d6, c7, s8, hQ]
Column 2: [h6, sJ, d2, hX, d5, cA, hA]
Column 3: [d9, dA, h7, d8, s3, sX, h2]
Column 4: [h8, sA, sK, hK, c6, s4, h9]
Column 5: [s5, c2, s7, s2, cQ, c5]
Column 6: [dK, cJ, cX, hJ, c4, c8]
Column 7: [dX, s6, c3, s9, d7, dJ]
Column 8: [d3, cK, sQ, h4, h3, d4]
Column 9: []
```

5. The objective of the game is to move all the cards to the piles, based on a set of move rules.
6. The game is solved when the Columns are empty while the Piles have cards placed in ascending order following the suits and faces.

```
Pile c: [cA, c1, c2, c3, c4, c5, c6, c7, c8, c9, cX, cJ, cQ, cK]
Pile d: [dA, d1, d2, d3, d4, d5, d6, d7, d8, d9, dX, dJ, dQ, dK]
Pile h: [hA, h1, h2, h3, h4, h5, h6, h7, h8, h9, hX, hJ, hQ, hK]
Pile s: [sA, s1, s2, s3, s4, s5, s6, s7, s8, s9, sX, sJ, sQ, sK]
Column 1: []
Column 2: []
Column 3: []
Column 4: []
Column 5: []
Column 6: []
Column 7: []
Column 8: []
Column 9: []
```

## Move Rules
1. Card(s) shall be moved from the end (right-most) of a Column to the end of another Column or the end of another Pile.
2. No card shall be moved from Piles.
3. When moving a card to a Pile, the card must be placed at the end of the Pile of the matching suit and the moved card must be one point bigger than the last (right-most) card at the Pile.
4. When moving a card to another Column, the suit and color are always ignored. The moved card must be placed to a Column where its last card is one point bigger than the moved card.
5. If you have a group of consecutive right-most cards whereby the faces are in order, you may move all of them to another Column in just one command.
6. If you have a group of consecutive right-most cards whereby the suits and faces are in order, you may move all of them to the Pile in just one command.

## How to Run
1. Compile all the Java files

```
javac *.java
```

2. Run the main Java file

```
java MainFunction
```

## How to Play
The game supports the following three (3) commands:
1. _`r`_ : Restart a new game.
2. _`x`_ : Exit the game.
3. _`source card destination`_ : Move _card_ from _source_ Column to _destination_ Column or Pile.