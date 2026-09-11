package JinRyuu.JRMCore.manager;

public class AttributeArray {
   public int currentID = 0;
   public Attribute[] attributes;
   public float lastValue;
   public boolean hasEnded = false;

   public AttributeArray() {
   }

   public AttributeArray(float... attributes) {
      this();
      this.attributes = new Attribute[attributes.length / 2];

      for (int i = 0; i < attributes.length; i += 2) {
         this.attributes[i / 2] = new Attribute(attributes[i], attributes[i + 1]);
      }
   }

   public AttributeArray(Attribute... attributes) {
      this();
      this.attributes = new Attribute[attributes.length];
      System.arraycopy(attributes, 0, this.attributes, 0, attributes.length);
   }

   public void update(float age) {
      if (!this.hasEnded) {
         if (age >= this.attributes[this.currentID + (this.currentID + 1 == this.attributes.length ? 0 : 1)].time) {
            if (this.currentID + 1 == this.attributes.length) {
               this.hasEnded = true;
               if (age > this.attributes[this.currentID].time) {
                  age = this.attributes[this.currentID].time;
               }
            }

            if (this.currentID + 1 != this.attributes.length) {
               this.currentID++;
            }
         }

         this.updateValue(age);
      }
   }

   public void updateValue(float age) {
      float currentValue;
      if (this.currentID + 1 == this.attributes.length) {
         currentValue = this.attributes[this.currentID].value;
      } else {
         float minValue = this.attributes[this.currentID].value;
         float maxValue = this.attributes[this.currentID + 1].value;
         float differenceValue = maxValue - minValue;
         float minTime = this.attributes[this.currentID].time;
         float maxTime = this.attributes[this.currentID + 1].time;
         float differenceTime = maxTime - minTime;
         float onePerTime = differenceValue / differenceTime;
         currentValue = (age - minTime) * onePerTime + this.attributes[this.currentID].value;
         if (maxValue > currentValue) {
            if (currentValue > maxValue) {
               currentValue = maxValue;
            }
         } else if (currentValue < maxValue) {
            currentValue = maxValue;
         }
      }

      this.lastValue = currentValue;
   }
}
