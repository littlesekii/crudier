const http = {
  baseUrl: "http://localhost:1001",
  get: function (url) {
    console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url);
  },
  post: function (url, body) {
    console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url, {
      method: "POST",
      body: body
    });
  },
  put: function (url, body) {
    console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url, {
      method: "PUT",
      body: body
    });
  },
  delete: function (url, body) {
    console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url, {
      method: "DELETE",
      body: body
    });
  },
};

export default http;