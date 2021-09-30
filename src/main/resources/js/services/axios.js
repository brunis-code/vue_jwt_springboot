import axios from 'axios'

export const Axios = axios.create({
    baseURL:  'http://localhost:9000',
    timeout: 10000,
  })

Axios.interceptors.request.use(function(config) {
    let user = JSON.parse(localStorage.getItem('user'));
    if (user && user.accessToken) {
        config.headers.Authorization ='Bearer ' +  user.accessToken;
        // config.headers.put('X-CSRF-TOKEN',  user.accessToken);
        // config.headers.put('Authorization', 'Bearer ' +  user.accessToken);

    } // for Spring Boot back-end
    // return { 'x-access-token': user.accessToken };       // for Node.js Express back-end

    return config;
}, function(err) {
    return Promise.reject(err);
});
