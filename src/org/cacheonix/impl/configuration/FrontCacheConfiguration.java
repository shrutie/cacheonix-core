/*
 * Cacheonix systems licenses this file to You under the LGPL 2.1
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.cacheonix.com/products/cacheonix/license-lgpl-2.1.htm
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cacheonix.impl.configuration;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * Class FrontCache.
 */
public final class FrontCacheConfiguration extends DocumentReader {

   /**
    * Field store.
    */
   private CacheStoreConfiguration store;


   /**
    * Returns the value of field 'store'.
    *
    * @return the value of field 'CacheStoreConfiguration'.
    */
   public CacheStoreConfiguration getStore() {

      return this.store;
   }


   /**
    * Sets the value of field 'store'.
    *
    * @param store the value of field 'store'.
    */
   public void setStore(final CacheStoreConfiguration store) {

      this.store = store;
   }


   protected void readNode(final String nodeName, final Node childNode) {

      if ("store".equals(nodeName)) {

         store = new CacheStoreConfiguration();
         store.read(childNode);
      }
   }


   protected void readAttribute(final String attributeName, final Attr attributeNode, final String attributeValue) {

      // This element doesn't have attributes yet
   }


   public String toString() {

      return "FrontCacheConfiguration{" +
              "store=" + store +
              '}';
   }
}