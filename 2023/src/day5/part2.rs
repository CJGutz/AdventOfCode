use crate::common::io::day_input;

#[derive(Debug)]
struct Range {
    start: isize,
    len: isize,
    mapped: bool,
}

fn max<T: PartialOrd>(a: T, b: T) -> T {
    match a.partial_cmp(&b) {
        Some(std::cmp::Ordering::Less) => b,
        _ => a,
    }
}

fn min<T: PartialOrd>(a: T, b: T) -> T {
    match a.partial_cmp(&b) {
        Some(std::cmp::Ordering::Greater) => b,
        _ => a,
    }
}

pub fn run() {
    let input = day_input(5);

    let categories = input.split("\n\n").map(|c| c.trim()).collect::<Vec<_>>();
    let mapped_seeds = categories
        .first()
        .unwrap()
        .split(": ")
        .nth(1)
        .unwrap()
        .split_whitespace()
        .map(|seed| seed.parse::<isize>().unwrap())
        .collect::<Vec<isize>>();
    let mut seed_ranges: Vec<Range> = Vec::new();
    let mut index = 0;
    while index + 1 < mapped_seeds.len() {
        let start = *mapped_seeds.get(index).unwrap();
        let range = *mapped_seeds.get(index + 1).unwrap();
        index += 2;
        seed_ranges.push(Range {
            start,
            len: range,
            mapped: false,
        });
    }

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

            let mut to_add: Vec<Range> = Vec::new();
            let mut to_remove: Vec<usize> = Vec::new();
            for (index, seed_range) in seed_ranges.iter().enumerate() {
                if seed_range.start > source_start + range
                    || (seed_range.start + seed_range.len) < source_start
                    || seed_range.mapped
                {
                    continue;
                }
                to_remove.push(index);
                let before_map = (seed_range.start, source_start - seed_range.start, false);
                let in_map = (
                    max(source_start, seed_range.start),
                    min(source_start + range, seed_range.start + seed_range.len)
                        - max(source_start, seed_range.start),
                    true,
                );
                let after_map = (
                    source_start + range,
                    (seed_range.start + seed_range.len) - source_start - range,
                    false,
                );
                for (start, len, to_map) in vec![before_map, in_map, after_map] {
                    if start < 0 || len <= 0 {
                        continue;
                    }
                    to_add.push(Range {
                        start: if to_map {
                            dis_start - source_start + start
                        } else {
                            start
                        },
                        len,
                        mapped: to_map,
                    })
                }
            }
            to_remove.reverse();
            for index in to_remove {
                seed_ranges.remove(index);
            }
            seed_ranges.extend(to_add);
        }
        seed_ranges = seed_ranges
            .iter()
            .map(|r| Range {
                start: r.start,
                len: r.len,
                mapped: false,
            })
            .collect();
    }
    seed_ranges.sort_by(|a, b| {
        a.start
            .partial_cmp(&b.start)
            .unwrap_or(std::cmp::Ordering::Equal)
    });
    println!("Best location: {:?}", seed_ranges.first().unwrap());
}
