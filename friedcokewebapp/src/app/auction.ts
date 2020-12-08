export interface Auction {
    id: string;
    item_name: string;
    description: string;
    category: string;
    startTime: string;
    endTime: string;
    status: string;
    highest_bidder: string;
    seller: string;
    start_price: number;
    curr_price: number;
    buynow_price: number;
    flag: boolean;
}
