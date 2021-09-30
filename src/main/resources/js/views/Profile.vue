<template>
  <v-card
          class="mx-auto"
          max-width="400"
  >

    <v-card-title>
      <v-avatar
              color="#e6e6e6"
              size="64"
      >
        <font-awesome-icon left icon="user"/>
      </v-avatar>

      <span class="title font-weight-light">{{currentUser.username}}</span>
    </v-card-title>

      <p>
        <strong>Token:</strong>
        {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
      </p>
      <p>
        <strong>Id:</strong>
        {{currentUser.id}}
      </p>
      <p>
        <strong>Email:</strong>
        {{currentUser.email}}
      </p>
      <strong>Authorities:</strong>
      <ul>
        <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
      </ul>


  </v-card>
</template>

<script>
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>