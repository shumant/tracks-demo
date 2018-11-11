import axios from 'axios';

export class APIService {

  constructor() {
  }

  // tracks
  getTracks() {
    const url = `tracks/`;
    return axios.get(url);
  }

  createTrack(track) {
    const url = `tracks/`;
    return axios.post(url, track);
  }

  updateTrack(track) {
    const url = `tracks/${track.id}`;
    return axios.put(url, track);
  }

  deleteTrack(track) {
    const url = `tracks/${track.id}`;
    return axios.delete(url);
  }

  // cars
  getCars(trackId) {
    const url = `tracks/${trackId}/cars`;
    return axios.get(url);
  }

  createCar(trackId, car) {
    const url = `tracks/${trackId}/cars`;
    return axios.post(url, car);
  }

  updateCar(car) {
    const url = `cars/${car.id}`;
    return axios.put(url, car);
  }

  deleteCar(carId) {
    const url = `cars/${carId}`;
    return axios.delete(url);
  }

}
