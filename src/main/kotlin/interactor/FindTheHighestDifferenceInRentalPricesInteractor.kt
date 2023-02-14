package interactor

import model.CityEntity

class FindTheHighestDifferenceInRentalPricesInteractor(private val dataSource: CostOfLivingDataSource) {
    fun execute(): CityEntity? {

        val citiesData = dataSource.getAllCitiesData()
        if (citiesData.isEmpty())
            return null
        val highestDifference = citiesData
            .filter(::isDataQuality)
            .maxBy {
                val oneBedroomDifference =
                    it.realEstatesPrices.apartmentOneBedroomInCityCentre!! -
                            it.realEstatesPrices.apartmentOneBedroomOutsideOfCentre!!

                val threeBedroomsDifference =
                    it.realEstatesPrices.apartment3BedroomsInCityCentre!! -
                            it.realEstatesPrices.apartment3BedroomsOutsideOfCentre!!
                maxOf(
                    oneBedroomDifference, threeBedroomsDifference,
                )
            }
        return highestDifference
    }

    private fun isDataQuality(city: CityEntity): Boolean {
        return city.dataQuality
    }
}