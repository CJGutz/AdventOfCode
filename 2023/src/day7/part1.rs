use crate::common::io::day_input;
use std::cmp::Ordering;

const LABEL_RANK: [&str; 13] = [
    "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A",
];

#[derive(PartialEq, Eq, Debug)]
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

fn of_a_kind(cards: &[&str; 5]) -> Vec<usize> {
    let mut counts = Vec::new();
    for card in cards {
        counts.push(cards.iter().filter(|c| (**c).eq(*card)).count());
    }
    counts
}

fn highest_card<'a>(first: &[&str; 5], second: &[&str; 5]) -> Ordering {
    for (a, b) in first.iter().zip(second.iter()) {
        let aindex = LABEL_RANK
            .iter()
            .enumerate()
            .find(|label| label.1 == a)
            .unwrap()
            .0;
        let bindex = LABEL_RANK
            .iter()
            .enumerate()
            .find(|label| label.1 == b)
            .unwrap()
            .0;

        match aindex.partial_cmp(&bindex).unwrap() {
            Ordering::Equal => continue,
            a => return a,
        }
    }
    Ordering::Equal
}

fn get_hand_type(cards: &[&str; 5]) -> HandType {
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
            if n_equal.iter().filter(|c| **c >= 2).count() > 3 {
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
    cards: [&'a str; 5],
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
                s.nth(0).unwrap(),
            )
        })
        .map(|(hand, bid)| {
            (
                [hand[0], hand[1], hand[2], hand[3], hand[4]],
                bid.parse::<usize>().unwrap(),
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
        let aindex = HAND_TYPE_RANK
            .iter()
            .enumerate()
            .find(|label| *label.1 == a.hand_type)
            .unwrap()
            .0;

        let bindex = HAND_TYPE_RANK
            .iter()
            .enumerate()
            .find(|label| *label.1 == b.hand_type)
            .unwrap()
            .0;

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
