use crate::common::io::day_input;
use std::cmp::Ordering;

const LABEL_RANK: [&str; 13] = [
    "J", "2", "3", "4", "5", "6", "7", "8", "9", "T", "Q", "K", "A",
];

#[derive(PartialEq, Eq, Debug, Clone)]
enum HandType {
    FiveKind,
    FourKind,
    FullHouse,
    ThreeKind,
    TwoPair,
    Pair,
    HighCard,
}

const HAND_TYPE_RANK: [HandType; 7] = [
    HandType::HighCard,
    HandType::Pair,
    HandType::TwoPair,
    HandType::ThreeKind,
    HandType::FullHouse,
    HandType::FourKind,
    HandType::FiveKind,
];

fn index_of<T: PartialEq>(array: &Vec<T>, item: &T) -> Option<usize> {
    let index = array.iter().enumerate().find(|(_, i)| *i == item);
    match index {
        Some((i, _)) => Some(i),
        None => None,
    }
}

fn of_a_kind(cards: &Vec<&str>) -> Vec<usize> {
    let mut counts: Vec<(usize, &str)> = Vec::new();
    let mut jokers = 0;
    let mut highest = 0;
    let mut best_card = "J";
    for card in cards {
        if card.to_owned().eq("J") {
            jokers += 1;
            continue;
        }

        let counted_cards = cards.iter().filter(|c| (**c).eq(*card)).count();
        if counted_cards > highest {
            highest = counted_cards;
            best_card = card;
        }
        counts.push((counted_cards, card));
    }
    let counts = counts
        .iter()
        .map(|(count, card)| {
            if best_card == *card {
                *count + jokers
            } else {
                *count
            }
        })
        .chain((0..jokers).map(|_| jokers + highest))
        .collect();

    counts
}

fn highest_card<'a>(first: &Vec<&str>, second: &Vec<&str>) -> Ordering {
    for (a, b) in first.iter().zip(second.iter()) {
        let aindex = index_of(&LABEL_RANK.to_vec(), a).unwrap();
        let bindex = index_of(&LABEL_RANK.to_vec(), b).unwrap();

        match aindex.partial_cmp(&bindex).unwrap() {
            Ordering::Equal => continue,
            a => return a,
        }
    }
    Ordering::Equal
}

fn get_hand_type(cards: &Vec<&str>) -> HandType {
    let mut n_equal = of_a_kind(cards);
    n_equal.sort();
    let highest = n_equal.last().unwrap();
    match highest {
        1 => HandType::HighCard,
        2 => {
            if n_equal.iter().filter(|c| **c >= 2).count() >= 3 {
                HandType::TwoPair
            } else {
                HandType::Pair
            }
        }
        3 => {
            if n_equal.iter().filter(|c| **c == 3).count() >= 3
                && n_equal.iter().filter(|c| **c == 2).count() >= 2
            {
                HandType::FullHouse
            } else {
                HandType::ThreeKind
            }
        }
        4 => HandType::FourKind,
        5 => HandType::FiveKind,
        _ => panic!("Not allowed with more equal cards"),
    }
}

#[derive(Debug)]
struct Hand<'a> {
    cards: Vec<&'a str>,
    bid: usize,
    hand_type: HandType,
}

pub fn run() {
    let input = day_input(7);

    let mut hands: Vec<Hand> = input
        .lines()
        .map(|l| l.split_whitespace())
        .map(|mut s| {
            (
                s.nth(0)
                    .unwrap()
                    .trim()
                    .splitn(6, "")
                    .skip(1)
                    .collect::<Vec<&str>>(),
                s.nth(0).unwrap().parse::<usize>().unwrap(),
            )
        })
        .map(|(cards, bid)| {
            let hand_type = get_hand_type(&cards);

            Hand {
                hand_type,
                cards,
                bid,
            }
        })
        .collect();

    hands.sort_by(|a, b| {
        let aindex = index_of(&HAND_TYPE_RANK.to_vec(), &a.hand_type);
        let bindex = index_of(&HAND_TYPE_RANK.to_vec(), &b.hand_type);

        match aindex.partial_cmp(&bindex).unwrap() {
            Ordering::Equal => highest_card(&a.cards, &b.cards),
            a => a,
        }
    });

    let result: usize = hands
        .iter()
        .enumerate()
        .map(|(count, hand)| hand.bid * (count + 1))
        .sum();
    println!("{result}");
}
