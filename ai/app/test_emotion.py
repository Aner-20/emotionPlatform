from emotion_service import EmotionService


texts = [
    "Oggi sono davvero felice, è stata una giornata fantastica!",
    "Sono molto triste e mi sento solo.",
    "Sono arrabbiato, questa situazione mi fa impazzire.",
    "Ho molta paura di quello che potrebbe succedere.",
    "Oggi è stata una giornata normale.",
    "Today, i'm really mad.",
    "I'm happy."
]


for text in texts:
    result = EmotionService.analyze_emotion(text)

    print("\nTESTO:")
    print(text)

    print("RISULTATO:")
    print(result)