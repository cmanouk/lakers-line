const path = require('path');

module.exports = {
  mode: 'production',
  entry: path.resolve(__dirname, './index.js'),
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader'],
      }
    ],
  },
  resolve: {
    extensions: ['*', '.js', '.jsx'],
  },
  output: {
    // path: path.resolve(__dirname, '../../your-favorite-lakers-backend/src/main/resources/static'),
    path: path.resolve(__dirname, '../public'),
    filename: 'bundle.js',
  }
};