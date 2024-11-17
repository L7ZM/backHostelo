// room.model.ts
export interface Room {
  id: number;
  roomNumber: string;
  type: string;
  price: number;
  status: 'AVAILABLE' | 'BOOKED' | 'OUTOFSERVICE';
  amenities: string[];
  image: string;
  description: string;
}
