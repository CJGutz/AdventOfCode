score = 0
prev_sack = None
with open("input.txt") as f:
    for i, sack in enumerate([line.strip() for line in f.readlines()]):
        sack = set(sack)
        if prev_sack:
            if i % 3 != 2:
                sack = sack.intersection(prev_sack)
                prev_sack = sack
            else:
                sack = sack.intersection(prev_sack)
                letter = sack.pop()
                score += ord(letter) - ((38) if letter.isupper() else (96))
                prev_sack = None
        else:
            prev_sack = sack
print(score)