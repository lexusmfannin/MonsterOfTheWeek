import axios from 'axios';

export default {

  get(id) {
    return axios.get(`/social`);
  },

  create(message) {
    return axios.post('/social/addComment', message);
  },

  // update(id, message) {
  //   return axios.put(`/social/${user_id}/${comment_id}`, message);
  // },

  delete(id) {
    return axios.delete(`/social/${id}`);
  },

}
