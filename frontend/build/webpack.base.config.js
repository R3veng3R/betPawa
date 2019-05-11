const path = require('path');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

const isDevMode = process.env.NODE_ENV !== 'production';

module.exports = {
    context: path.resolve(),
    entry: {
        app: "./src/main.js"
    },

    output: {
        publicPath: "/",
        filename: 'js/[name].bundle.js',
        chunkFilename: 'js/[id].chunk.js'
    },

    resolve: {
        extensions: ['.js', '.vue', '.json'],

        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            '@': path.resolve('src')
        }
    },

    optimization: {
        runtimeChunk: 'single',
        splitChunks: {
            chunks: 'all'
        }
    },

    module: {
        rules: [
            {
                test: /^(?!.*\.spec\.|.*\.test\.js$).*\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            },

            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },

            {
                test: /\.(sa|sc|c)ss$/,
                exclude: /node_modules/,
                use: [
                    isDevMode ? 'style-loader' : MiniCssExtractPlugin.loader,
                    'css-loader',
                    'postcss-loader',
                    'sass-loader'
                ],
            },
        ]
    },

    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: './src/index.html',
            chunksSortMode: 'dependency',
            inject: true,
            hash: true
        }),
    ]
};