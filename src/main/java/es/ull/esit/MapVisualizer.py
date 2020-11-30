# Libraries
import sys
import folium
import pandas as pd

path = "./utilities/transports.csv"
dataFrame = pd.read_csv(path, delimiter=';')

# Create empty map
m = folium.Map(location=[0, 0], tiles="Stamen Terrain", zoom_start=6)

# Adding markers
# https://python-visualization.github.io/folium/quickstart.html
for i in range(0, len(dataFrame)):
    folium.Marker([dataFrame.iloc[i]['LATITUD'], dataFrame.iloc[i]['LONGITUD']],
                  popup=dataFrame.iloc[i]['TIPO']).add_to(m)

m.save('testdata.html')
sys.exit(0)
