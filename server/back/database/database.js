const mongoose = require("mongoose")

async function connectDB() {
    try {
        const url = "mongodb://db:27017/putsvile"
        await mongoose.connect(url, {
            useUnifiedTopology: true,
            useNewUrlParser: true
        })
        console.log("Connected to database")
    } catch (err) {
        console.log(`Error: ${err.message}`)
    }
}

module.exports = connectDB
