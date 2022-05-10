const express = require("express")
const router = express()

const connectDB = require("./back/database/database")
connectDB()

router.use(express.urlencoded({ extended: false }))
router.use(express.json())

const uslugeRoute = require("./back/controller/usluga")
router.use("/api", uslugeRoute)

const radniciRoute = require("./back/controller/radnik")
router.use("/api", radniciRoute)

const port = 3000
router.listen(port, () => {
    console.log("Listening on port: " + port)
})