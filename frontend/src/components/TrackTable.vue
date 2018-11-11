<template>
  <div>
    <h1>Tracks ()</h1>


    <table class="table table-bordered table-hover">

      <thead>

      <tr>

        <th>#</th>

        <th>Name</th>
        <th>Description</th>
        <th>Email</th>
        <th>Phone</th>
        <th> Address</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="track in tracks">

        <th>{ { track.id } }</th>

        <th>{ { track.name } }</th>

        <td>{ { track.description } }</td>

        <td>

          <button class="btn btn-danger" @click="deleteTrack(track)"> X</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div>
    </div>
  </div>

</template>


<script>
  import {APIService} from '../APIService';

  const apiService = new APIService();

  export default {

    name: 'ListTracks',

    components: {},

    data() {

      return {
        tracks: [],
        numberOfTracks: 0,

      };

    },

    methods: {

      getTracks() {
        apiService.getTracks().then((data) => {

          this.tracks = data.data;
          this.numberOfTracks = data.count;
        });
      },
    },

    mounted() {
      this.getTracks();
    },
  }
</script>
