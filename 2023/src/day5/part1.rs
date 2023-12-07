use crate::common::io::day_input;

pub fn run() {
    let input = day_input(5);

    let categories = input.split("\n\n").map(|c| c.trim()).collect::<Vec<_>>();
    let mut mapped_seeds = categories
        .first()
        .unwrap()
        .split(": ")
        .nth(1)
        .unwrap()
        .split_whitespace()
        .map(|seed| (seed.parse::<isize>().unwrap(), false))
        .collect::<Vec<(isize, bool)>>();

    let categories = categories
        .iter()
        .map(|cat| {
            cat.lines()
                .collect::<Vec<_>>()
                .split_first()
                .unwrap()
                .1
                .to_vec()
        })
        .collect::<Vec<Vec<&str>>>();

    for category in categories {
        for line in category {
            let ranges = line
                .splitn(3, " ")
                .map(|num| num.parse::<isize>().unwrap())
                .collect::<Vec<isize>>();
            let dis_start = ranges[0];
            let source_start = ranges[1];
            let range = ranges[2];

            mapped_seeds = mapped_seeds
                .iter()
                .map(|(seed, mapped)| {
                    if !*mapped && *seed >= source_start && *seed < source_start + range {
                        (*seed + dis_start - source_start, true)
                    } else {
                        (*seed, *mapped)
                    }
                })
                .collect();
        }
        mapped_seeds = mapped_seeds
            .iter()
            .map(|(item, _mapped)| (*item, false))
            .collect();
    }

    mapped_seeds.sort();

    println!(
        "Best location: {}",
        mapped_seeds.first().expect("Empty list").0
    );
}
