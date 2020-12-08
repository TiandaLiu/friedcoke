import {Auction} from './auction';

export const MOCK_AUCTIONS: Auction[] = [
    {
        id: "7d2f4c53-1815-4d43-96cd-45feafa8cf41",
        item_name: "Nintendo Switch with Blue and Red Joy-Con Controllers and Mario Kart 8 Bundle - Black",
        description: "Nintendo Switch with Blue and Red Joy-Con Controllers and Mario Kart 8 Bundle -. Condition is \"New\". Shipped with UPS Ground.",
        category: "clothing",
        startTime: "2020-02-02",
        endTime: "2021-02-02",
        status: "active",
        highest_bidder: 'tianda',
        seller: 'sunan',
        start_price: 1,
        curr_price: 2,
        buynow_price: 10,
        flag: false
    },
    {
        id: "ae0fd89d-4690-4f48-aabb-1d76d8b36acd",
        item_name: "coffee",
        description: "a cup of coffee",
        category: "life",
        startTime: "2020-02-01",
        endTime: "2021-02-01",
        status: "active",
        highest_bidder: 'chaoqin',
        seller: 'tianda',
        start_price: 1,
        curr_price: 2,
        buynow_price: 10,
        flag: false
    }
  ];
