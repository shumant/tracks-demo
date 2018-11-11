import axios from 'axios';

export class APIService {

  constructor() {
  }

  getTracks() {
    const url = `tracks/`;
    return axios.get(url);
  }

  getContact(pk) {
    const url = `${API_URL}/api/contacts/${pk}`;
    return axios.get(url).then(response = > response.data
  )
    ;
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

}
