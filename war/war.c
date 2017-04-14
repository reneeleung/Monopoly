#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void initialDeck(int deck[]){
  int suit = 2;
  int count = 0;
  for (int row = 0; row < 52; row++){
    deck[row] = suit;
    count++;
    if (count == 4){
      suit++;
      count = 0;
    }
  }
}
void shuffleDeck(int deck[]){
  for (int i = 0; i < 52; i++){
    int random = rand()%52;
    int temp = deck[i];
    deck[i] = deck[random];
    deck[random] = temp;
  }
}

int showTopCard(int deck[], int len){
  int top = deck[0];
  for (int i = 0; i < len - 1; i++){
    deck[i] = deck[i + 1];
  }
  return top;
}

int main(void) {
  char player1[50];
  char player2[50];
  int deck1[104];
  int deck2[104];
  int remain1 = 52;
  int remain2 = 52;
  printf("Enter player 1's name\n");
  scanf("%s", player1);
  printf("Enter player 2's name\n");
  scanf("%s", player2);
  initialDeck(deck1);
  initialDeck(deck2);
  shuffleDeck(deck1);
  shuffleDeck(deck2);

  while (remain1 > 0 && remain2 > 0){
    int card1 = showTopCard(deck1, remain1);
    remain1--;
    int card2 = showTopCard(deck2, remain2);
    remain2--;
    printf("%s: Face Up = %d\n", player1, card1);
    printf("%s: Face Up = %d\n", player2, card2);
    if (card1 > card2){
      deck1[remain1] = card1;
      deck1[remain1 + 1] = card2;
      remain1 += 2;
      printf("%s takes both cards!\n", player1);
    } else if (card1 < card2){
      deck2[remain2] = card1;
      deck2[remain2 + 1] = card2;
      remain2 += 2;
      printf("%s takes both cards!\n", player2);
    } else {
      // War
      if (remain1 == 0 || remain2 == 0){
        break;
      }
      bool war = true;
      int cardsofar[52];
      int sofarlen = 0;
      while (war){
        printf("War!\n");
        int down1 = showTopCard(deck1, remain1);
        remain1--;
        int down2 = showTopCard(deck2, remain2);
        remain2--;
        printf("%s: Face Down\n", player1);
        printf("%s: Face Down\n", player2);
        if (remain1 == 0 || remain2 == 0){
          break;
        }
        int up1 = showTopCard(deck1, remain1);
        remain1--;
        int up2 = showTopCard(deck2, remain2);
        remain2--;
        printf("%s: Face Up = %d\n", player1, up1);
        printf("%s: Face Up = %d\n", player2, up2);
        if (up1 > up2){
          for (int i = 0; i < sofarlen; i++){
            deck1[remain1 + i] = cardsofar[i];
          }
          remain1 += sofarlen;
          // Take card1, card2, down1, down2, up1, up2
          deck1[remain1] = card1;
          deck1[remain1 + 1] = card2;
          deck1[remain1 + 2] = down1;
          deck1[remain1 + 3] = down2;
          deck1[remain1 + 4] = up1;
          deck1[remain1 + 5] = up2;
          remain1 += 6;
          printf("%s takes %d cards!\n", player1, sofarlen + 6);
          war = false;
        } else if (up1 < up2){
          for (int i = 0; i < sofarlen; i++){
            deck2[remain2 + i] = cardsofar[i];
          }
          remain2 += sofarlen;
          // Take card1, card2, down1, down2, up1, up2
          deck2[remain2] = card1;
          deck2[remain2 + 1] = card2;
          deck2[remain2 + 2] = down1;
          deck2[remain2 + 3] = down2;
          deck2[remain2 + 4] = up1;
          deck2[remain2 + 5] = up2;
          remain2 += 6;
          printf("%s takes %d cards!\n", player2, sofarlen + 6);
          war = false;
        } else {
          cardsofar[sofarlen] = down1;
          cardsofar[sofarlen + 1] = down2;
          cardsofar[sofarlen + 2] = up1;
          cardsofar[sofarlen + 3] = up2;
          sofarlen += 4;
        }
      }
    }
  }
  if (remain1 == 0 && remain2 == 0){
    printf("It's a tie\n");
  } else if (remain1 == 0){
    printf("%s wins!\n", player2);
    printf("%s has %d cards\n", player1, remain1);
    printf("%s has %d cards\n", player2, remain2);
  } else if (remain2 == 0){
    printf("%s wins!\n", player1);
    printf("%s has %d cards\n", player1, remain1);
    printf("%s has %d cards\n", player2, remain2);
  }
}
